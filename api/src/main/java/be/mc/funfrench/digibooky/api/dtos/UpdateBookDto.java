package be.mc.funfrench.digibooky.api.dtos;

public class UpdateBookDto {
    private String title;
    private String authorLastName;
    private String authorFirstName;

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public UpdateBookDto withTitle(String title) {
        this.title = title;
        return this;
    }

    public UpdateBookDto withAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public UpdateBookDto withAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }
}
