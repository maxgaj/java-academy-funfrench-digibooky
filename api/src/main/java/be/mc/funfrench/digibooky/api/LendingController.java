package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.LendingDto;
import be.mc.funfrench.digibooky.api.dtos.ReturnLendingDto;
import be.mc.funfrench.digibooky.api.mappers.LendingMapper;
import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.domain.Lending;
import be.mc.funfrench.digibooky.domain.users.Member;
import be.mc.funfrench.digibooky.infrastructure.BookNotFoundException;
import be.mc.funfrench.digibooky.infrastructure.InvalidLendingException;
import be.mc.funfrench.digibooky.infrastructure.LendingNotFoundException;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import be.mc.funfrench.digibooky.service.repositories.LendingRepository;
import be.mc.funfrench.digibooky.service.repositories.MemberRepository;
import be.mc.funfrench.digibooky.service.validators.LendingValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Api(tags = "Lending Resource")
@RestController
@RequestMapping(path = LendingController.LENDING_CONTROLLER_RESOURCE_URL)
public class LendingController {

    static final String LENDING_CONTROLLER_RESOURCE_URL = "/lendings";
    private Logger logger = LoggerFactory.getLogger(LendingController.class);


    private MemberRepository memberRepository;
    private BookRepository bookRepository;
    private LendingRepository lendingRepository;
    private LendingValidator lendingValidator;
    private LendingMapper lendingMapper;

    @Autowired
    public LendingController(MemberRepository memberRepository, BookRepository bookRepository,
                             LendingRepository lendingRepository, LendingValidator lendingValidator, LendingMapper lendingMapper) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.lendingValidator = lendingValidator;
        this.lendingRepository = lendingRepository;
        this.lendingMapper = lendingMapper;
    }

    @ApiOperation("Creates a new lending based on a User ID and a Book ID")
    @PostMapping(path="/book/{bookIsbn}/user/{userId}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public LendingDto createLending(@PathVariable String bookIsbn, @PathVariable String userId){
        Member member = memberRepository.findOneOrNullById(userId);
        Book book = bookRepository.findOneOrNullAvailableBookByIsbn(bookIsbn);
        lendingValidator.validate(book, member);
        Lending lending = new Lending(member, book);
        lendingRepository.persist(lending);
        bookRepository.updateLentStatus(book.getId(), true);
        return lendingMapper.mapToDto(lending);
    }

    @ExceptionHandler({InvalidLendingException.class, BookNotFoundException.class})
    protected void invalidLendingCreationException(RuntimeException e, HttpServletResponse response) throws IOException {
        logger.error("Impossible to create lending: " + e.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ApiOperation("Return a book based on the Lending ID.")
    @PostMapping(path="/book/{lendingId}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ReturnLendingDto returnABook(@PathVariable String lendingId) {
         Lending lending = lendingRepository.deleteById(lendingId);
         bookRepository.updateLentStatus(lending.getBook().getId(), false);
         if(LocalDate.now().isAfter(lending.getDueDate())) {
             return ((ReturnLendingDto)lendingMapper.mapToDto(lending))
                     .withDelayMessage(DAYS.between(lending.getDueDate(), LocalDate.now()));
         }
         return ((ReturnLendingDto)lendingMapper.mapToDto(lending))
                 .withoutDelayMessage();
    }

    @ExceptionHandler(LendingNotFoundException.class)
    protected void lendingNotFoundException(LendingNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
