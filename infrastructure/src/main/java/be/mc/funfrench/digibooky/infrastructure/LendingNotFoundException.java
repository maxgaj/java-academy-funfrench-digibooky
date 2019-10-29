package be.mc.funfrench.digibooky.infrastructure;

public class LendingNotFoundException extends RuntimeException {

    public LendingNotFoundException(String message) {
        super(message);
    }
}
