package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.api.mappers.BookMapper;
import be.mc.funfrench.digibooky.infrastructure.BookNotFoundException;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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
    public List<BookDto> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get filtered books by isbn")
    @GetMapping(params = {"isbn"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooksByIsbn(@RequestParam String isbn) {
            return bookRepository.findByIsbn(isbn).stream()
                    .map(bookMapper::toBookDto)
                    .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get filtered books by title")
    @GetMapping(params = {"title"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooksByTitle(@RequestParam String title) {
            return bookRepository.findByTitle(title).stream()
                    .map(bookMapper::toBookDto)
                    .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get filtered books by author name")
    @GetMapping(params = {"authorName"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooksByAuthorName(@RequestParam String authorName) {
        return bookRepository.findByAuthor(authorName).stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get book from library for the given id")
    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(@PathVariable String bookId) {
        return bookMapper.toBookDto(bookRepository.findBookById(bookId));
    }

    @ExceptionHandler(BookNotFoundException.class)
    protected void bookIdNotFoundException(BookNotFoundException ex,
                                           HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }
}
