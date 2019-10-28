package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.BaseUser;
import be.mc.funfrench.digibooky.domain.users.Librarian;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LibrarianRepositoryTest {

    @Mock
    private BaseUserRepository baseUserRepository;
    @InjectMocks
    private LibrarianRepository librarianRepository;

    @Test
    void findAll_givenNewRepository_thenReturnEmptyCollection() {
        Collection<Librarian> librarians = librarianRepository.findAll();
        Assertions.assertThat(librarians).isEmpty();
    }

    @Test
    void persist_givenLibrarian_returnCorrectLibrarian() {
        Librarian librarian = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        Mockito.when(baseUserRepository.persist(librarian)).thenReturn(librarian);
        BaseUser savedLibrarian = librarianRepository.persist(librarian);

        Assertions.assertThat(savedLibrarian).isEqualTo(librarian);
    }

    @Test
    void persist_givenLibrarian_thenReturnRightLibrarian() {
        Librarian librarian = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        BaseUserRepository repository = new BaseUserRepository();
        int size = repository.findAll().size();
        repository.persist(librarian);

        Assertions.assertThat(repository.findAll().size()).isEqualTo(size+1);

    }

    @Test
    void findAll_givenRepositoryWithLibrarians_thenReturnEmptyCollection() {
        Librarian librarian = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        Mockito.when(baseUserRepository.persist(librarian)).thenReturn(librarian);
        List<BaseUser> librarians = new ArrayList<>();
        librarians.add(librarian);
        Mockito.when(baseUserRepository.findAllByStatus(librarian.getStatus())).thenReturn(librarians);
        librarianRepository.persist(librarian);
        Collection<Librarian> librarianUsers = librarianRepository.findAll();

        Assertions.assertThat(librarianUsers).containsExactly(librarian);
    }

    @Test
    void countById_givenEmptyDb_thenReturnZero() {
        Assertions.assertThat(librarianRepository.countById("TEST")).isEqualTo(0);
    }

    @Test
    void countById_givenNonEmptyDb_thenReturnCorrectCount(){
        Librarian librarian = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        librarian.setId("TEST");
        Mockito.when(baseUserRepository.persist(librarian)).thenReturn(librarian);
        List<BaseUser> members = new ArrayList<>();
        members.add(librarian);
        Mockito.when(baseUserRepository.findAllByStatus(librarian.getStatus())).thenReturn(members);
        librarianRepository.persist(librarian);
        Assertions.assertThat(librarianRepository.countById("TEST")).isEqualTo(1);
    }

    @Test
    void countByEmail_givenEmptyDb_thenReturnZero(){
        Assertions.assertThat(librarianRepository.countByEmail("TEST")).isEqualTo(0);
    }

    @Test
    void countByEmail_givenNonEmptyDb_thenReturnCorrectCount(){
        Librarian librarian = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        Mockito.when(baseUserRepository.persist(librarian)).thenReturn(librarian);
        List<BaseUser> librarians = new ArrayList<>();
        librarians.add(librarian);
        Mockito.when(baseUserRepository.findAllByStatus(librarian.getStatus())).thenReturn(librarians);
        librarianRepository.persist(librarian);
        Assertions.assertThat(librarianRepository.countByEmail(librarian.getEmail())).isEqualTo(1);
    }
}