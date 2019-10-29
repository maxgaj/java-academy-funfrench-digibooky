package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.CreateBookDto;
import be.mc.funfrench.digibooky.api.dtos.UpdateBookDto;
import be.mc.funfrench.digibooky.api.mappers.BookMapper;
import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import be.mc.funfrench.digibooky.service.validators.BookValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookControllerTest {

    private BookController bookController;
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        bookController = new BookController(bookRepository, new BookMapper(), new BookValidator(bookRepository));
    }

    @Test
    void GivenValidCreatedBookDto_RegisterBookToBookRepository() {
        CreateBookDto createBookDto= new CreateBookDto()
                .withTitle("testBook")
                .withAuthorLastName("me")
                .withIsbn13("00-00000-11-1");

        Book bookToRegister = bookController.registerNewBook(createBookDto);
//        bookRepository.persistNewBookToRepository(bookToRegister);

//        Assertions.assertEquals("me", bookToRegister.getAuthorLastName());
//        Assertions.assertEquals("00-00000-11-1", bookToRegister.getIsbn13());
//        Assertions.assertEquals("testBook", bookToRegister.getTitle());
    }

    @Test
    void GivenABook_WhenDeleted_ThenSetTrueToIsDeleted() {
        Book book = Book.BookBuilder.bookBuilder()
                .withAuthorLastName("blabla")
                .withIsbn13("11-11111-11-1")
                .withTitle("testTitle")
                .build();
        bookRepository.persistNewBookToRepository(book);

        bookController.deleteBook(book.getId());
        Assertions.assertTrue(book.isDeleted());
    }

//    @Test
//    void GivenAnUpdateBook_ThenBookIsUpdateWithValuesOfUpdateBook(){
//        UpdateBookDto updateBookDto= new UpdateBookDto()
//                .withAuthorFirstName("Tolkien");
//        Book book= new Book.BookBuilder()
//                .withAuthorLastName("blabla")
//                .withIsbn13("11-11111-11-1")
//                .withTitle("testTitle")
//                .withId()
//                .build();
//
//
//        bookController.updateBook(book.getId(), updateBookDto);
//        Assertions.assertEquals("Tolkien", book.getAuthorFirstName());
//    }
}