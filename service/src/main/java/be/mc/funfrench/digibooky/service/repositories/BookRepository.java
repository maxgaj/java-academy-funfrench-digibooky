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

@Component
public class BookRepository {

    private final ConcurrentHashMap<String, Book> booksById;
    private final Logger logger = LoggerFactory.getLogger(BookRepository.class);
    private final ConcurrentHashMap<String, Book> deletedBooks;

    public BookRepository() {
        Book book1 = new Book.BookBuilder()
                .withAuthorFirstName("JK")
                .withAuthorLastName("Rowling")
                .withTitle("Harry Potter ")
                .withIsbn13("12-12345-34-5")
                .withId()
                .build();
        Book book2 = new Book.BookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("IT")
                .withIsbn13("12-54321-65-5")
                .withId()
                .build();
        Book book3 = new Book.BookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("The Dark Tower")
                .withIsbn13("12-12123-34-5")
                .withId()
                .build();
        Book book4 = new Book.BookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("The Long Walk")
                .withIsbn13("98-76543-21-0")
                .withId()
                .build();
        Book book5 = new Book.BookBuilder()
                .withAuthorFirstName("Andrzej")
                .withAuthorLastName("Sapkowski")
                .withTitle("The Withcer")
                .withIsbn13("77-88888-99-0")
                .withId()
                .build();
        Book book6 = new Book.BookBuilder()
                .withAuthorFirstName("Jean-Paul")
                .withAuthorLastName("Sartre")
                .withTitle("Les mains sales")
                .withIsbn13("00-00000-00-0")
                .withId()
                .build();
        Book book7 = new Book.BookBuilder()
                .withAuthorFirstName("Marc")
                .withAuthorLastName("Levy")
                .withTitle("Et si c'était vrai")
                .withIsbn13("22-22222-22-2")
                .withId()
                .build();
        Book book8 = new Book.BookBuilder()
                .withAuthorFirstName("Bernard")
                .withAuthorLastName("Werber")
                .withTitle("The ants")
                .withIsbn13("33-33333-33-3")
                .withId()
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
        this.deletedBooks= new ConcurrentHashMap<>();
    }

    public Collection<Book> findAll() {
        return booksById.values();
    }

    public boolean checkIsbnFormat(String isbn){
        String regex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})" +
                "[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(isbn);
        return matcher.matches();
    }

        public List<Book> findByIsbn(String isbn){
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

    public void registerNewBookToRepository(Book book){
        this.booksById.put(book.getIsbn13(), book);
    }

    /**
     * Find a book for the given id.
     * @param bookId The id of the searched book
     * @return book for the given id or null if no book was found
     * @throws NullPointerException if the bookId is null
     */
    public Book findBookById(String bookId) throws BookNotFoundException {
        Book book = booksById.get(bookId);
        if(book == null) {
            throw new BookNotFoundException("No book was found for the given id: '" + bookId + "'.");
        }
        return book;
    }

    public void deleteBookFromRepository(String id) {

    }
}

