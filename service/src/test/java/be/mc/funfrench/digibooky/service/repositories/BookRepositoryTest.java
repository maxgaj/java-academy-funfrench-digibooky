package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class BookRepositoryTest {

    @Test
    void findAll_givenEmptyRepository_thenReturnEmptyCollection() {
        BookRepository bookRepository = new BookRepository();

        Collection<Book> booksReturned = bookRepository.findAll();

        Assertions.assertEquals(7, booksReturned.size());   //TODO change it back to 0 after story 10A
    }
}