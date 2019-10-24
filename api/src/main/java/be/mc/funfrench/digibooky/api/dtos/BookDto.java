package be.mc.funfrench.digibooky.api.dtos;

public class BookDto {

    private String isbn;
    private String authorLastName;
    private String authorFirstName;
    private String title;

    public String getIsbn() {
        return isbn;
    }

    public BookDto withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public BookDto withAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public BookDto withAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDto withTitle(String title) {
        this.title = title;
        return this;
    }
}
