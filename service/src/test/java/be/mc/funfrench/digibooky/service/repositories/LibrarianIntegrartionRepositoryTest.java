package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.Librarian;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LibrarianIntegrartionRepositoryTest {

    @Test
    void givenEmptyRepository_whenPersistLibrarians_thenLibrariansHaveId() {
        LibrarianRepository librarianRepository = new LibrarianRepository(new BaseUserRepository());
        Librarian librarian = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        Librarian savedLibrarian = librarianRepository.persist(librarian);

        Assertions.assertThat(savedLibrarian.getId()).isNotNull();
    }

    @Test
    void givenNonEmptyRepository_whenFindAll_thenReturnCorrectCollection() {
        LibrarianRepository librarianRepository = new LibrarianRepository(new BaseUserRepository());
        Librarian librarian1 = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        librarianRepository.persist(librarian1);
        Librarian librarian2 = new Librarian("4567", "Lino", "Linelli", "lino@gmail.com");
        librarianRepository.persist(librarian2);

        Assertions.assertThat(librarianRepository.findAll()).containsExactlyInAnyOrder(librarian1, librarian2);
    }

    @Test
    void givenNonEmptyRepository_whenCountByEmail_thenReturnCorrectCount() {
        LibrarianRepository librarianRepository = new LibrarianRepository(new BaseUserRepository());
        Librarian librarian = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        librarianRepository.persist(librarian);
        Assertions.assertThat(librarianRepository.countByEmail("pino@gmail.com")).isEqualTo(1);
    }

    @Test
    void givenNonEmptyRepository_whenCountById_thenReturnCorrectCount() {
        LibrarianRepository librarianRepository = new LibrarianRepository(new BaseUserRepository());
        Librarian librarian = new Librarian("1234", "Pino", "Pinelli", "pino@gmail.com");
        Librarian savedLibrarian = librarianRepository.persist(librarian);
        Assertions.assertThat(librarianRepository.countById(savedLibrarian.getId())).isEqualTo(1);

    }
}