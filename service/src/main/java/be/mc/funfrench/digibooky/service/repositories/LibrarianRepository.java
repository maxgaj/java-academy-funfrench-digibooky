package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.Librarian;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LibrarianRepository {

    private final  BaseUserRepository repository;

    public LibrarianRepository(BaseUserRepository repository) {
        this.repository = repository;
    }

    public Collection<Librarian> findAll() {
        return null;
    }

    public Librarian persist(Librarian librarian) {

        return librarian;
    }

    public long countByEmail(String email) {
        return findAll().stream()
                .filter(librarian -> librarian.getEmail().equals(email))
                .count();

    }

    public long countById(String id) {
        return findAll().stream()
                .filter(librarian -> librarian.getId().equals(id))
                .count();
    }
}
