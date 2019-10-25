package be.mc.funfrench.digibooky.service.repositories;


import be.mc.funfrench.digibooky.domain.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookRepository {

    private final ConcurrentHashMap<String, Book> booksByIsbn;

    public BookRepository() {
        Book book1 = new Book.BookBuilder()
                .withAuthorFirstName("JK")
                .withAuthorLastName("Rowling")
                .withTitle("Harry Potter ")
                .withIsbn13("12-12345-34-5")
                .build();

        Book book2 = new Book.BookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("IT")
                .withIsbn13("12-54321-65-5")
                .build();
        Book book3 = new Book.BookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("The Dark Tower")
                .withIsbn13("12-12345-34-5")
                .build();
        Book book4 = new Book.BookBuilder()
                .withAuthorFirstName("Stephen")
                .withAuthorLastName("King")
                .withTitle("The Long Walk")
                .withIsbn13("98-76543-21-0")
                .build();
        Book book5 = new Book.BookBuilder()
                .withAuthorFirstName("Andrzej")
                .withAuthorLastName("Sapkowski")
                .withTitle("The Withcer")
                .withIsbn13("77-88888-99-0")
                .build();
        Book book6 = new Book.BookBuilder()
                .withAuthorFirstName("Jean-Paul")
                .withAuthorLastName("Sartre")
                .withTitle("Les mains sales")
                .withIsbn13("00-00000-00-0")
                .build();
        Book book7 = new Book.BookBuilder()
                .withAuthorFirstName("Marc")
                .withAuthorLastName("Levy")
                .withTitle("Et si c'était vrai")
                .withIsbn13("22-22222-22-2")
                .build();
        Book book8 = new Book.BookBuilder()
                .withAuthorFirstName("Bernard")
                .withAuthorLastName("Werber")
                .withTitle("The ants")
                .withIsbn13("33-33333-33-3")
                .build();
        this.booksByIsbn = new ConcurrentHashMap<>();
        booksByIsbn.put(book1.getIsbn13(), book1);
        booksByIsbn.put(book2.getIsbn13(), book2);
        booksByIsbn.put(book3.getIsbn13(), book3);
        booksByIsbn.put(book4.getIsbn13(), book4);
        booksByIsbn.put(book5.getIsbn13(), book5);
        booksByIsbn.put(book6.getIsbn13(), book6);
        booksByIsbn.put(book7.getIsbn13(), book7);
        booksByIsbn.put(book8.getIsbn13(), book8);
    }

//    public void persist(Book book){
//        booksByIsbn.put(book.getIsbn13(), book );
//    }

    public Collection<Book> findAll() {
        return booksByIsbn.values();
    }
}

