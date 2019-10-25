package be.mc.funfrench.digibooky.api.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All information about the book")
public class BookDto {

    @ApiModelProperty(notes = "The isbn13 of the book")
    private String isbn13;

    @ApiModelProperty(notes = "The author Last Name of the book")
    private String authorLastName;

    @ApiModelProperty(notes = "The author First Name of the book")
    private String authorFirstName;

    @ApiModelProperty(notes = "The title of the book")
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
