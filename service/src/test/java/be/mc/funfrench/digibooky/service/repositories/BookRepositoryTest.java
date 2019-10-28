package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class BookRepositoryTest {

    private BookRepository bookRepository;

    @BeforeEach
    void initialize() {
        bookRepository = new BookRepository();
    }

    @Test
    void findAll_givenEmptyRepository_thenReturnEmptyCollection() {

        Collection<Book> booksReturned = bookRepository.findAll();

        Assertions.assertEquals(7, booksReturned.size());   //TODO change it back to 0 after story 10A
    }

    @Test
    void findByTitle_givenInputWithPartOfTitle_thenReturnLisOfBookThatMatchesPartOfTheTitle() {

        String input = "The*";

        List<Book> booksReturned = bookRepository.findByTitle(input);

        Assertions.assertEquals(booksReturned.size(),4);
    }

    @Test
    void findByAuthor_givenInputAuthorNameStartWithS_thenReturnListOfBooksForAllAuthorsThatFirstnameOrLastnameStartWithS() {

        String input = "S*";
        List<Book> booksReturned = bookRepository.findByAuthor(input);

        assertThat(booksReturned.stream()
                    .map(Book::getAuthorLastName)
                    .collect(Collectors.toList()))
                .containsOnly("King", "Sapkowski", "Sartre");
        assertThat(booksReturned.stream()
                    .map(Book::getAuthorFirstName)
                    .collect(Collectors.toList()))
                .containsOnly("Stephen", "Andrzej", "Jean-Paul");
        assertThat(booksReturned.stream()
                    .map(Book::getTitle)
                    .collect(Collectors.toList()))
                .containsExactlyInAnyOrder("IT", "The Long Walk", "The Withcer", "The Dark Tower", "Les mains sales");
    }

    @Test
    void findByAuthor_givenInputAuthorLastNameStartWithSAndFirstNameStartWithA_thenReturnListOfBooksOfSapkowskiAndrzej() {

        String input = "S*A*";
        List<Book> booksReturned = bookRepository.findByAuthor(input);

        assertThat(booksReturned.stream()
                .map(Book::getAuthorLastName)
                .collect(Collectors.toList()))
                .containsOnly("Sapkowski");
        assertThat(booksReturned.stream()
                .map(Book::getAuthorFirstName)
                .collect(Collectors.toList()))
                .containsOnly("Andrzej");
        assertThat(booksReturned.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList()))
                .containsExactlyInAnyOrder("The Withcer");
    }

    @Test
    void findByAuthor_givenInputAuthorFirstNameStartWithAAndLastNameStartWithS_thenReturnListOfBooksOfSapkowskiAndrzej() {

        String input = "A*S*";
        List<Book> booksReturned = bookRepository.findByAuthor(input);

        assertThat(booksReturned.stream()
                .map(Book::getAuthorLastName)
                .collect(Collectors.toList()))
                .containsOnly("Sapkowski");
        assertThat(booksReturned.stream()
                .map(Book::getAuthorFirstName)
                .collect(Collectors.toList()))
                .containsOnly("Andrzej");
        assertThat(booksReturned.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList()))
                .containsExactlyInAnyOrder("The Withcer");
    }

    @Test
    void findByAuthor_givenInputSartreJeanPaul_thenReturnListOfBooksOfSartreJeanPaul() {

        String input = "Sartre Jean-Paul";
        List<Book> booksReturned = bookRepository.findByAuthor(input);

        assertThat(booksReturned.stream()
                .map(Book::getAuthorLastName)
                .collect(Collectors.toList()))
                .containsOnly("Sartre");
        assertThat(booksReturned.stream()
                .map(Book::getAuthorFirstName)
                .collect(Collectors.toList()))
                .containsOnly("Jean-Paul");
        assertThat(booksReturned.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList()))
                .containsExactlyInAnyOrder("Les mains sales");
    }
}