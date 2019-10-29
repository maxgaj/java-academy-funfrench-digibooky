package be.mc.funfrench.digibooky.infrastructure;

public class InvalidLendingException extends RuntimeException {
    public InvalidLendingException(String message) {
        super(message);
    }
}
