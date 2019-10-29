package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.api.dtos.CreateBookDto;
import be.mc.funfrench.digibooky.api.dtos.UpdateBookDto;
import be.mc.funfrench.digibooky.api.mappers.BookMapper;
import be.mc.funfrench.digibooky.infrastructure.BookNotFoundException;
import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import be.mc.funfrench.digibooky.service.validators.BookValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookRepository bookRepository, BookMapper bookMapper, BookValidator bookValidator) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookValidator = bookValidator;
    }

    @ApiOperation(value = "Get all books from library")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get filtered books by isbn")
    @GetMapping(params = {"isbn"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooksByIsbn(@RequestParam String isbn) {
        return bookRepository.findByIsbn(isbn).stream()
                .map(bookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get filtered books by title")
    @GetMapping(params = {"title"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooksByTitle(@RequestParam String title) {
        return bookRepository.findByTitle(title).stream()
                .map(bookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get filtered books by author name")
    @GetMapping(params = {"authorName"}, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooksByAuthorName(@RequestParam String authorName) {
        return bookRepository.findByAuthor(authorName).stream()
                .map(bookMapper::mapToBookDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get book from library for the given id")
    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(@PathVariable String bookId) {
        return bookMapper.mapToBookDto(bookRepository.findBookById(bookId));
    }

    @ExceptionHandler(BookNotFoundException.class)
    protected void bookIdNotFoundException(BookNotFoundException ex,
                                           HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ApiOperation(value = "Register new Book")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public Book registerNewBook(@RequestBody CreateBookDto createBookDto) {
        Book bookToRegister = bookMapper.createBookDtoToBook(createBookDto);
        if (!bookValidator.validateBook(bookToRegister)) {
            System.err.println("book infos not valid");
        }return bookRepository.persistNewBookToRepository(bookToRegister);
    }//TODO NEED HELP TO USE THE LOGGER TO THROW EXCEPTION (alexis)


    @ApiOperation(value = "Soft Delete Book")
    @DeleteMapping(path = "/{bookId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void deleteBook(@PathVariable String bookId) {
        Book book = bookRepository.findBookById(bookId);
        book.setDeleted(true);
    }

    @ApiOperation(value = "Update Book")
    @PutMapping(path = "/{bookId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void updateBook(@PathVariable String bookId, @RequestBody UpdateBookDto updateBookDto) {
        Book book = bookRepository.findBookById(bookId);
        bookMapper.updateBookDtoToBook(book, updateBookDto);
    }
}
