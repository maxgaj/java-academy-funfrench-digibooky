package be.mc.funfrench.digibooky.infrastructure;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
