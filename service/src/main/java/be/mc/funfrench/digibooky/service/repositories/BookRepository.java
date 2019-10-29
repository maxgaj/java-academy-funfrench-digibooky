package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.infrastructure.BookNotFoundException;
import com.yevdo.jwildcard.JWildcard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class BookRepository {

    private final ConcurrentHashMap<String, Book> booksById;
    private final Logger logger = LoggerFactory.getLogger(BookRepository.class);

    public BookRepository() {
        Book book1 = Book.BookBuilder.bookBuilder()
                .withAuthorFirstName("JK")
                .withAuthorLastName("Rowling")
                .withTitle("Harry Potter ")
                .withIsbn13("12-12345-34-5")
                .build();
        Book book2 = Book.BookBuilder.bookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("IT")
                .withIsbn13("12-54321-65-5")
                .build();
        Book book3 = Book.BookBuilder.bookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("The Dark Tower")
                .withIsbn13("12-12123-34-5")
                .build();
        Book book4 = Book.BookBuilder.bookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("The Long Walk")
                .withIsbn13("98-76543-21-0")
                .build();
        Book book5 = Book.BookBuilder.bookBuilder()
                .withAuthorFirstName("Andrzej")
                .withAuthorLastName("Sapkowski")
                .withTitle("The Withcer")
                .withIsbn13("77-88888-99-0")
                .build();
        Book book6 = Book.BookBuilder.bookBuilder()
                .withAuthorFirstName("Jean-Paul")
                .withAuthorLastName("Sartre")
                .withTitle("Les mains sales")
                .withIsbn13("00-00000-00-0")
                .build();
        Book book7 = Book.BookBuilder.bookBuilder()
                .withAuthorFirstName("Marc")
                .withAuthorLastName("Levy")
                .withTitle("Et si c'était vrai")
                .withIsbn13("22-22222-22-2")
                .build();
        Book book8 = Book.BookBuilder.bookBuilder()
                .withAuthorFirstName("Bernard")
                .withAuthorLastName("Werber")
                .withTitle("The ants")
                .withIsbn13("33-33333-33-3")
                .build();
        this.booksById = new ConcurrentHashMap<>();
        booksById.put(book1.getId(), book1);
        booksById.put(book2.getId(), book2);
        booksById.put(book3.getId(), book3);
        booksById.put(book4.getId(), book4);
        booksById.put(book5.getId(), book5);
        booksById.put(book6.getId(), book6);
        booksById.put(book7.getId(), book7);
        booksById.put(book8.getId(), book8);
    }

    public Collection<Book> findAll() {
        return booksById.values().stream()
                .filter(book -> !book.isDeleted())
                .collect(Collectors.toList());
    }

    public boolean checkIsbnFormat(String isbn) {
        String regex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})" +
                "[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(isbn);
        return matcher.matches();
    }

    public List<Book> findByIsbn(String isbn) {
        String partOfTitleConvertedToRegex = JWildcard.wildcardToRegex(isbn);
        List<Book> returnedBooks = new ArrayList<>();
        Pattern pattern = Pattern.compile(partOfTitleConvertedToRegex);
        for (Book book : findAll()) {
            if (pattern.matcher(book.getIsbn13()).matches()) {
                returnedBooks.add(book);
            }
        }
        return returnedBooks;
    }

    public List<Book> findByTitle(String partOfTitle) {
        String partOfTitleConvertedToRegex = JWildcard.wildcardToRegex(partOfTitle);
        List<Book> returnedBooks = new ArrayList<>();
        Pattern pattern = Pattern.compile(partOfTitleConvertedToRegex);
        for (Book book : findAll()) {
            if (pattern.matcher(book.getTitle()).matches()) {
                returnedBooks.add(book);
            }
        }
        return returnedBooks;
    }

    /**
     * Search all books for the given author's part / full name.
     *
     * @param authorName The author's firstname, lastname, or both with wildcard.
     * @return all books of the givens author
     */
    public List<Book> findByAuthor(String authorName) {
        List<Book> authorBooks = new ArrayList<>();
        Pattern pattern = Pattern.compile(JWildcard.wildcardToRegex(authorName));
        for (Book book : findAll()) {
            if (pattern.matcher(book.getAuthorLastName()).matches() ||
                    pattern.matcher(book.getAuthorFirstName()).matches() ||
                    pattern.matcher(book.getAuthorFirstName().concat(" ").concat(book.getAuthorLastName())).matches() ||
                    pattern.matcher(book.getAuthorLastName().concat(" ").concat(book.getAuthorFirstName())).matches()) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    public Book persistNewBookToRepository(Book book) {
        return this.booksById.put(book.getId(), book);
    }

    /**
     * Find a book for the given id.
     *
     * @param bookId The id of the searched book
     * @return book for the given id
     * @throws BookNotFoundException if the book was not found
     */
    public Book findBookById(String bookId) throws BookNotFoundException {
        Book book = booksById.get(bookId);
        if (book == null) {
                logger.error(createBookNotFoundMessage(bookId));
                throw new BookNotFoundException(createBookNotFoundMessage(bookId));
        }
        return book;
    }

    private String createBookNotFoundMessage(String bookId) {
        return "No book was found for the given id: '" + bookId + "'.";
    }

    public Book findOneOrNullAvailableBookByIsbn(String bookIsbn) {
        return findByIsbn(bookIsbn).stream()
                .filter(book -> !book.isLent())
                .findFirst()
                .orElse(null);
    }

    public void deleteBook(String id) {
        Book book = findBookById(id);
        book.setDeleted(true);
    }


    public void updateLentStatus(String bookId, boolean isLent) {
        findBookById(bookId).setLent(isLent);
    }
}



