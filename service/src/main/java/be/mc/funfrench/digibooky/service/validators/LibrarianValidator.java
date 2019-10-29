package be.mc.funfrench.digibooky.service.validators;

import be.mc.funfrench.digibooky.domain.users.Librarian;
import be.mc.funfrench.digibooky.service.repositories.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class LibrarianValidator {

    private LibrarianRepository librarianRepository;

    @Autowired
    public LibrarianValidator(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    public boolean validate(Librarian librarian) {
        hasLastName(librarian);
        hasEmail(librarian);
        hasValidEmail(librarian);
        hasUniqueEmail(librarian);
        return true;
    }

    private void hasLastName(Librarian librarian) {
        if(librarian.getLastName() == null || librarian.getLastName().isEmpty()){
            throw new IllegalArgumentException("Librarian should have a last name");
        }
    }

    private void hasEmail(Librarian librarian) {
        if(librarian.getEmail() == null || librarian.getEmail().isEmpty()){
            throw new IllegalArgumentException("Librarian should have an email");
        }
    }
    private void hasValidEmail(Librarian librarian) {
        String regex = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(librarian.getEmail()).matches()){
            throw new IllegalArgumentException("Email should be formatted as x@x.x");
        }

    }

    private void hasUniqueEmail(Librarian librarian) {
        if(librarianRepository.countByEmail(librarian.getEmail()) > 0){
            throw new IllegalArgumentException("The email should be unique");
        }

    }

}
