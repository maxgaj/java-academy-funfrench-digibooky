package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.api.dtos.CreateBookDto;
import be.mc.funfrench.digibooky.api.mappers.BookMapper;
import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation(value = "Register new Book")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void registerNewBook(@RequestParam String title, @RequestParam String isbn, @RequestParam String authorLastname) throws IllegalAccessError {
        try{
        CreateBookDto createBookDto= new CreateBookDto()
                .withIsbn13(isbn)
                .withAuthorLastName(authorLastname)
                .withTitle(title);
        Book bookToRegister = bookMapper.createBookDtoToBook(createBookDto);
        bookRepository.registerNewBookToRepository(bookToRegister);
        }catch(IllegalAccessError ex){
            System.err.println("only a librarian can add a new book");;
        }//TODO NEED HELP TO USE THE LOGGER TO THROW EXCEPTION (alexis)
    }

    @ApiOperation(value = "Soft Delete Book")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void deleteBook(@RequestParam String id ) {
        bookRepository.deleteBookFromRepository(id);
    }

    @ApiOperation(value = "Update Book")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('LIBRARIAN')")
    public void UpdateBook(BookDto bookDto) {
    }
}
