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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Book Resource")
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

    @ApiOperation(value = "Find a book by title")
    @GetMapping(path = "/{title}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBookByTitle(@PathVariable String title){
        return bookRepository.findByTitle(title).stream().map(bookMapper::toBookDto)
                .collect(Collectors.toList());

    }


}
