package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.LendingDto;
import be.mc.funfrench.digibooky.api.mappers.LendingMapper;
import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.domain.Lending;
import be.mc.funfrench.digibooky.domain.users.Member;
import be.mc.funfrench.digibooky.infrastructure.BookNotFoundException;
import be.mc.funfrench.digibooky.infrastructure.InvalidLendingException;
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

@Api(tags = "Lending Resource")
@RestController
@RequestMapping(path = LendingController.LENDING_CONTROLLER_RESOURCE_URL)
public class LendingController {

    public static final String LENDING_CONTROLLER_RESOURCE_URL = "/lendings";
    private Logger logger = LoggerFactory.getLogger(LendingController.class);


    private MemberRepository memberRepository;
    private BookRepository bookRepository;
    private LendingRepository lendingRepository;
    private LendingValidator lendingValidator;
    private LendingMapper lendingMapper;

    @Autowired
    public LendingController(MemberRepository memberRepository, BookRepository bookRepository, LendingRepository lendingRepository, LendingValidator lendingValidator, LendingMapper lendingMapper) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.lendingValidator = lendingValidator;
        this.lendingRepository = lendingRepository;
        this.lendingMapper = lendingMapper;
    }

    @ApiOperation("Creates a new lending based on a User ID and a Book ID")
    @PostMapping(path="/book/{bookId}/user/{userId}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public LendingDto createLending(@PathVariable String bookId, @PathVariable String userId){
        Member member = memberRepository.findOneOrNullById(userId);
        Book book = bookRepository.findBookById(bookId);
        lendingValidator.validate(book, member);
        Lending lending = new Lending(member, book);
        lendingRepository.persist(lending);
        return lendingMapper.mapToDto(lending);
    }

    


    @ExceptionHandler(InvalidLendingException.class)
    protected void invalidLendingCreationException(InvalidLendingException e, HttpServletResponse response) throws IOException {
        logger.error("Impossible to create lending: " + e.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    protected void bookNotFoundException(InvalidLendingException e, HttpServletResponse response) throws IOException {
        logger.error("Impossible to create lending: " + e.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    @ApiOperation("Return a book based on the Lending ID.")
    @PostMapping(path="/book/{bookId}/user/{userId}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public LendingDto returnABook(@PathVariable String lendingId){
        
        return lendingMapper.mapToDto(lending);
    }
}
