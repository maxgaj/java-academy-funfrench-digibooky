package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.api.dtos.CreateBookDto;
import be.mc.funfrench.digibooky.api.dtos.UpdateBookDto;
import be.mc.funfrench.digibooky.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapToDomain(BookDto bookDto) {
        return Book.BookBuilder.bookBuilder()
                .withIsbn13(bookDto.getIsbn13())
                .withAuthorFirstName(bookDto.getAuthorFirstName())
                .withAuthorLastName(bookDto.getAuthorLastName())
                .withTitle(bookDto.getTitle())
                .build();
    }

    public BookDto mapToBookDto(Book book) {
        return new BookDto()
                .withId(book.getId())
                .withIsbn13(book.getIsbn13())
                .withAuthorFirstName(book.getAuthorFirstName())
                .withAuthorLastName(book.getAuthorLastName())
                .withTitle(book.getTitle())
                .withIsLent(book.isLent());
    }

    public Book createBookDtoToBook(CreateBookDto createBookDto) {
        return Book.BookBuilder.bookBuilder()
                .withIsbn13(createBookDto.getIsbn13())
                .withAuthorLastName(createBookDto.getAuthorLastName())
                .withTitle(createBookDto.getTitle())
                .build();
    }

    public Book updateBookDtoToBook(Book book, UpdateBookDto updateBookDto) {
            book.setTitle(updateBookDto.getTitle());
            book.setAuthorFirstName(updateBookDto.getAuthorFirstName());
            book.setAuthorLastName(updateBookDto.getAuthorLastName());
            return book;
    }
}
