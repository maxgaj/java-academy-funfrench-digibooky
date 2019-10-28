//package be.mc.funfrench.digibooky.api;
//
//import be.mc.funfrench.digibooky.api.dtos.CreateBookDto;
//import be.mc.funfrench.digibooky.api.dtos.UpdateBookDto;
//import be.mc.funfrench.digibooky.domain.Book;
//import be.mc.funfrench.digibooky.service.repositories.BookRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//class BookControllerTest {
//    private BookController bookController;
//    private BookRepository bookRepository;
//
//    @Autowired
//    BookControllerTest(BookController bookController, BookRepository bookRepository) {
//        this.bookController = bookController;
//        this.bookRepository = bookRepository;
//    }
//
//    @Test
//    void GivenValidCreatedBookDto_RegisterBookToBookRepository() {
//        CreateBookDto createBookDto= new CreateBookDto()
//                .withTitle("testBook")
//                .withAuthorLastName("me")
//                .withIsbn13("00-00000-11-1");
//
//        Book bookToRegister = bookController.registerNewBook(createBookDto);
//
//        Assertions.assertEquals("me", bookToRegister.getAuthorLastName());
//        Assertions.assertEquals("00-00000-11-1", bookToRegister.getIsbn13());
//        Assertions.assertEquals("testBook", bookToRegister.getTitle());
//        Assertions.assertEquals(9, bookRepository.findAll().size());
//    }
//
//    @Test
//    void GivenABook_WhenDeleted_ThenSetTrueToIsDeleted() {
//        Book book= new Book.BookBuilder()
//                .withAuthorLastName("blabla")
//                .withIsbn13("11-11111-11-1")
//                .withTitle("testTitle")
//                .withId()
//                .build();
//
//        bookController.deleteBook(book.getId());
//        Assertions.assertTrue(book.isDeleted());
//    }
//
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
//        bookController.UpdateBook(book.getId(), updateBookDto);
//        Assertions.assertEquals("Tolkien", book.getAuthorFirstName());
//    }
//}