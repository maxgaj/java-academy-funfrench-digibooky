package be.mc.funfrench.digibooky.api.dtos;

public class BookDto {

    private String isbn13;
    private String authorLastName;
    private String authorFirstName;
    private String title;

    public String getIsbn13() {
        return isbn13;
    }

    public BookDto withIsbn13(String isbn13) {
        this.isbn13 = isbn13;
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
