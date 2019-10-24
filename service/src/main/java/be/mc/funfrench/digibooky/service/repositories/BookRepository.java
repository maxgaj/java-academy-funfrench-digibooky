package be.mc.funfrench.digibooky.service.repositories;


import be.mc.funfrench.digibooky.domain.Book;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class BookRepository {

    private final ConcurrentHashMap<String, Book> booksByIsbn;

    public BookRepository() {
        this.booksByIsbn = new ConcurrentHashMap<>();
    }

    public Collection<Book> findAll(){
        return booksByIsbn.values();
    }
}
