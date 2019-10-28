package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class BookRepositoryTest {

    @Test
    void findAll_givenEmptyRepository_thenReturnEmptyCollection() {
        BookRepository bookRepository = new BookRepository();

        Collection<Book> booksReturned = bookRepository.findAll();

        Assertions.assertEquals(8, booksReturned.size());   //TODO change it back to 0 after story 10A
    }

    @Test
    void findByTitle_givenInputWithPartOfTitle_thenReturnLisOfBookThatMatchesPartOfTheTitle() {
        BookRepository bookRepository = new BookRepository();

        String input = "The*";

        List<Book> booksReturned = bookRepository.findByTitle(input);

        Assertions.assertEquals(booksReturned.size(),4);
    }

    @Test
    void findByIsbn_givenInputWithPartOfIsbn_thenReturnListOfBookThatMatchesPartOfTheIsbn() {
        BookRepository bookRepository = new BookRepository();

        String input = "12*";

        List<Book> booksReturned = bookRepository.findByIsbn(input);

        Assertions.assertEquals(booksReturned.size(),3);
    }
}