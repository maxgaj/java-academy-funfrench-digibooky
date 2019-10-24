package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Test
    void toDto_givenABook_whenMapToDto_thenReturnABookDto() {
        Book book = new Book.BookBuilder()
                .withIsbn13("9780733426094")
                .withAuthorFirstName("Laura")
                .withAuthorLastName("Depape")
                .withTitle("Les mavens")
                .build();
        BookMapper bookMapper = new BookMapper();

        BookDto bookDto = bookMapper.toBookDto(book);

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

        Book bookReturned = bookMapper.toBook(bookDto);

        Assertions.assertEquals(bookReturned.getIsbn13(), bookDto.getIsbn13());
        Assertions.assertEquals(bookReturned.getAuthorFirstName(), bookDto.getAuthorFirstName());
        Assertions.assertEquals(bookReturned.getAuthorLastName(), bookDto.getAuthorLastName());
        Assertions.assertEquals(bookReturned.getTitle(), bookDto.getTitle());
    }
}