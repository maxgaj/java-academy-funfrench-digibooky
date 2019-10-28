package be.mc.funfrench.digibooky.domain.users;

public enum UserStatus {
    MEMBER("MEMBER"),
    ADMIN("ADMIN"),
    LIBRARIAN("LIBRARIAN");

    private String label;

    UserStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
