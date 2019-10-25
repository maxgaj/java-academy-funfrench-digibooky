package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.api.mappers.BookMapper;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Books Resource")
@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @ApiOperation(value = "Get all books from library")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get books of ")
    @GetMapping(path = "/authors", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooksByAuthor(@RequestParam(required = false) String authorName) {
        if(authorName == null) {
            return getAllBooks();
        }
        return bookRepository
                .findByAuthorName(authorName)
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

}
