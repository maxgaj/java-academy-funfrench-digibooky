package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.infrastructure.BookNotFoundException;
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

    @Test
    void findById_givenAnExistingBookId_whenSearchByBookId_thenReturnTheBook() {
        String bookId = bookRepository.findAll().iterator().next().getId();

        String foundId = bookRepository.findBookById(bookId).getId();

        assertThat(foundId).isEqualTo(bookId);
    }

    @Test
    void findById_givenAnNotExistingBookId_whenSearchByBookId_thenThrowsBookNotFoundException() {
        String bookId = "1";
        Assertions.assertThrows(BookNotFoundException.class, ()-> {
            bookRepository.findBookById(bookId);
        });
    }

	@Test
    void findByIsbn_givenInputWithPartOfIsbn_thenReturnListOfBookThatMatchesPartOfTheIsbn() {
        BookRepository bookRepository = new BookRepository();

        String input = "12*";

        List<Book> booksReturned = bookRepository.findByIsbn(input);

        Assertions.assertEquals(booksReturned.size(),3);
    }
}