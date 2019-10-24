package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toBook(BookDto bookDto){
        return new Book.BookBuilder()
                .withIsbn(bookDto.getIsbn())
                .withAuthorFirstName(bookDto.getAuthorFirstName())
                .withAuthorLastName(bookDto.getAuthorLastName())
                .withTitle(bookDto.getTitle())
                .build();
    }

    public BookDto toBookDto(Book book){
        return new BookDto()
                .withIsbn(book.getIsbn())
                .withAuthorFirstName(book.getAuthorFirstName())
                .withAuthorLastName(book.getAuthorLastName())
                .withTitle(book.getTitle());
    }
}
