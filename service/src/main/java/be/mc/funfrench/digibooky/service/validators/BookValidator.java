package be.mc.funfrench.digibooky.service.validators;

import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {
    private BookRepository bookRepository;

    @Autowired
    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean validateBook(Book book) {
        hasTitle(book);
        hasIsbn(book);
        hasAuthorLastName(book);
        return true;
    }

    public boolean hasTitle(Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Book must have a title");
        }
        return true;
    }

    public boolean hasAuthorLastName(Book book) {
        if (book.getAuthorLastName() == null || book.getAuthorLastName().isEmpty()) {
            throw new IllegalArgumentException("Book must have an author lastname");
        }
        return true;
    }

    public boolean hasIsbn(Book book) {
        if (book.getIsbn13() == null || book.getIsbn13().isEmpty()) {
            throw new IllegalArgumentException("Book must have an ISBN");
        }
        return true;
    }
}

