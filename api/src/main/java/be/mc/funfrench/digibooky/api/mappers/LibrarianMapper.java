package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.CreateLibrarianDto;
import be.mc.funfrench.digibooky.api.dtos.LibrarianDto;
import be.mc.funfrench.digibooky.domain.users.Librarian;
import org.springframework.stereotype.Component;

@Component
public class LibrarianMapper {

    public LibrarianDto mapToDto(Librarian librarian){
        return new LibrarianDto()
                .withId(librarian.getId())
                .withFirstName(librarian.getFirstName())
                .withLastName(librarian.getLastName())
                .withEmail(librarian.getEmail());
    }

    public Librarian mapToLibrarian(CreateLibrarianDto createLibrarianDto){
        return new Librarian(createLibrarianDto.getPassword(),
                createLibrarianDto.getFirstName(),
                createLibrarianDto.getLastName(),
                createLibrarianDto.getEmail());
    }
}
