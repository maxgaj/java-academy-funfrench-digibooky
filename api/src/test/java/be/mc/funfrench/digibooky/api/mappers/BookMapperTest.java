package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.api.dtos.UpdateBookDto;
import be.mc.funfrench.digibooky.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookMapperTest {

    @Test
    void toDto_givenABook_whenMapToDto_thenReturnABookDto() {
        Book book = Book.BookBuilder.bookBuilder()
                .withIsbn13("9780733426094")
                .withAuthorFirstName("Laura")
                .withAuthorLastName("Depape")
                .withTitle("Les mavens")
                .build();
        BookMapper bookMapper = new BookMapper();

        BookDto bookDto = bookMapper.mapToBookDto(book);

        Assertions.assertEquals(book.getIsbn13(), bookDto.getIsbn13());
        Assertions.assertEquals(book.getAuthorFirstName(), bookDto.getAuthorFirstName());
        Assertions.assertEquals(book.getAuthorLastName(), bookDto.getAuthorLastName());
        Assertions.assertEquals(book.getTitle(), bookDto.getTitle());
    }

    @Test
    void toBook_givenABookDto_whenMapToBook_thenReturnABook() {

        BookDto bookDto =
                new BookDto()
                        .withIsbn13("9780733426094")
                        .withAuthorFirstName("Laura")
                        .withAuthorLastName("Depape")
                        .withTitle("Les mavens");

        BookMapper bookMapper = new BookMapper();

        Book bookReturned = bookMapper.mapToDomain(bookDto);

        Assertions.assertEquals(bookReturned.getIsbn13(), bookDto.getIsbn13());
        Assertions.assertEquals(bookReturned.getAuthorFirstName(), bookDto.getAuthorFirstName());
        Assertions.assertEquals(bookReturned.getAuthorLastName(), bookDto.getAuthorLastName());
        Assertions.assertEquals(bookReturned.getTitle(), bookDto.getTitle());
    }

    @Test
    void updateBookDtoToBook_givenABookAndAnUpdateBookDto_thenReturnTheSameId() {
        Book book = Book.BookBuilder.bookBuilder()
                .withIsbn13("9780733426094")
                .withAuthorFirstName("Laura")
                .withAuthorLastName("Depape")
                .withTitle("Les mavens")
                .build();

        UpdateBookDto updateBookDto = new UpdateBookDto();
        updateBookDto.withTitle("Hanna");
        updateBookDto.withAuthorFirstName("Green");
        updateBookDto.withAuthorLastName("Rabbia");
        BookMapper bookMapper = new BookMapper();
        Book update = bookMapper.updateBookDtoToBook(book, updateBookDto);

        Assertions.assertEquals(book.getId(), update.getId());
        Assertions.assertEquals(book.getTitle(), update.getTitle());
        Assertions.assertEquals(book.getAuthorFirstName(), update.getAuthorFirstName());
        Assertions.assertEquals(book.getAuthorLastName(), update.getAuthorLastName());
    }

    @Test
    void updateBookDtoToBook_givenABookAndAnUpdateBookDto_thenReturnAnUpdatedTitle() {
        Book book = Book.BookBuilder.bookBuilder()
                .withIsbn13("9780733426094")
                .withAuthorFirstName("Laura")
                .withAuthorLastName("Depape")
                .withTitle("Les mavens")
                .build();

        UpdateBookDto updateBookDto = new UpdateBookDto();
        updateBookDto.withTitle("Hanna");
        updateBookDto.withAuthorFirstName("Green");
        updateBookDto.withAuthorLastName("Rabbia");
        BookMapper bookMapper = new BookMapper();
        Book update = bookMapper.updateBookDtoToBook(book, updateBookDto);

        Assertions.assertEquals(book.getTitle(), update.getTitle());

    }

    @Test
    void updateBookDtoToBook_givenABookAndAnUpdateBookDto_thenReturnAnUpdatedAuthorFirstName() {
        Book book = Book.BookBuilder.bookBuilder()
                .withIsbn13("9780733426094")
                .withAuthorFirstName("Laura")
                .withAuthorLastName("Depape")
                .withTitle("Les mavens")
                .build();

        UpdateBookDto updateBookDto = new UpdateBookDto();
        updateBookDto.withTitle("Hanna");
        updateBookDto.withAuthorFirstName("Green");
        updateBookDto.withAuthorLastName("Rabbia");
        BookMapper bookMapper = new BookMapper();
        Book update = bookMapper.updateBookDtoToBook(book, updateBookDto);

        Assertions.assertEquals(book.getAuthorFirstName(), update.getAuthorFirstName());

    }
}