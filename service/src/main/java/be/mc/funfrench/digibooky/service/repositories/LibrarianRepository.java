package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.Librarian;
import be.mc.funfrench.digibooky.domain.users.UserStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class LibrarianRepository {

    private final  BaseUserRepository repository;

    public LibrarianRepository(BaseUserRepository repository) {
        this.repository = repository;
    }

    public Collection<Librarian> findAll() {
        return repository.findAllByStatus(UserStatus.LIBRARIAN).stream()
                .map(librarian -> (Librarian) librarian)
                .collect(Collectors.toList());
    }

    public Librarian persist(Librarian librarian) {
        return (Librarian) repository.persist(librarian);
    }

    public long countByEmail(String email) {
        return findAll().stream()
                .filter(librarian -> librarian.getEmail().equals(email))
                .count();

    }

    long countById(String id) {
        return findAll().stream()
                .filter(librarian -> librarian.getId().equals(id))
                .count();
    }
}
