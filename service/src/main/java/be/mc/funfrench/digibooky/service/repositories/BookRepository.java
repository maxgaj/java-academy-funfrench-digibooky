package be.mc.funfrench.digibooky.service.repositories;


import be.mc.funfrench.digibooky.domain.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookRepository {

    private final ConcurrentHashMap<String, Book> booksByIsbn;

    public BookRepository() {
        this.booksByIsbn = new ConcurrentHashMap<>();
    }

    public Collection<Book> findAll(){
        return booksByIsbn.values();
    }

    public List<Book> findByTitle(String title) {
        List<Book> returnedBooks = new ArrayList<>();
        Collection<Book> books = findAll();
        for (Book book:books) {
            if(book.getTitle().equals(title)){
                returnedBooks.add(book);
            }
        }
        return returnedBooks;
    }
}
