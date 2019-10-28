package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Book;
import com.yevdo.jwildcard.JWildcard;
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
        this.deletedBooks= new ConcurrentHashMap<>();
        this.booksById = new ConcurrentHashMap<>();
        booksById.put(book1.getIsbn13(), book1);
        booksById.put(book2.getIsbn13(), book2);
        booksById.put(book3.getIsbn13(), book3);
        booksById.put(book4.getIsbn13(), book4);
        booksById.put(book5.getIsbn13(), book5);
        booksById.put(book6.getIsbn13(), book6);
        booksById.put(book7.getIsbn13(), book7);
        booksById.put(book8.getIsbn13(), book8);
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

    public void registerNewBookToRepository(Book book){
        this.booksById.put(book.getIsbn13(), book);
    }

    public void deleteBookFromRepository(String id){
//        this.deletedBooks.put(findByIdid));
//        this.booksById.remove(findByIdid));
    }
}

//TODO it would be better to use another class in service to search and find inside the repos(alexis)