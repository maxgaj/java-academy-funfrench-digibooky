package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toBook(BookDto bookDto){
        return new Book.BookBuilder()
                .withIsbn13(bookDto.getIsbn13())
                .withAuthorFirstName(bookDto.getAuthorFirstName())
                .withAuthorLastName(bookDto.getAuthorLastName())
                .withTitle(bookDto.getTitle())
                .build();
    }

    public BookDto toBookDto(Book book){
        return new BookDto()
                .withIsbn13(book.getIsbn13())
                .withAuthorFirstName(book.getAuthorFirstName())
                .withAuthorLastName(book.getAuthorLastName())
                .withTitle(book.getTitle());
    }
}
