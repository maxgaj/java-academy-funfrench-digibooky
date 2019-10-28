package be.mc.funfrench.digibooky.api.dtos;

public class CreateBookDto {
    private String isbn13;
    private String title;
    private String authorLastName;

    public CreateBookDto withIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }

    public CreateBookDto withTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateBookDto withAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }
}
