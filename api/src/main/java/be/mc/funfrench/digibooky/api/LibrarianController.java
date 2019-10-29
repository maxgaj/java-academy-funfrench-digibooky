package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.CreateLibrarianDto;
import be.mc.funfrench.digibooky.api.dtos.LibrarianDto;
import be.mc.funfrench.digibooky.api.mappers.LibrarianMapper;
import be.mc.funfrench.digibooky.domain.users.Librarian;
import be.mc.funfrench.digibooky.service.repositories.LibrarianRepository;
import be.mc.funfrench.digibooky.service.validators.LibrarianValidator;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Librarian Resource")
@RestController
@RequestMapping(path = LibrarianController.LIBRARIAN_CONTROLLER_RESOURCE_URL)
public class LibrarianController {
    static final String LIBRARIAN_CONTROLLER_RESOURCE_URL = "/librarians";
    private LibrarianRepository librarianRepository;
    private LibrarianMapper librarianMapper;
    private LibrarianValidator librarianValidator;

    @Autowired
    public LibrarianController(LibrarianRepository librarianRepository, LibrarianMapper librarianMapper, LibrarianValidator librarianValidator) {
        this.librarianRepository = librarianRepository;
        this.librarianMapper = librarianMapper;
        this.librarianValidator = librarianValidator;
    }

    @ApiOperation(value = "Get all Librarians")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<LibrarianDto> getAllLibrarians() {
        return librarianRepository.findAll().stream()
                .map(librarian -> librarianMapper.mapToDto(librarian))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Register a new Librarian")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public LibrarianDto registerLibrarian(@RequestBody CreateLibrarianDto createLibrarianDto) {
        Librarian librarianToCreate = librarianMapper.mapToLibrarian(createLibrarianDto);
        librarianValidator.validate(librarianToCreate);
        return librarianMapper.mapToDto(
                librarianRepository.persist(
                        librarianToCreate
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected void invalidMemberRegistrationException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


}
