package be.mc.funfrench.digibooky.domain;

public class Book {

    private final String isbn;
    private final String authorLastName;
    private final String authorFirstName;
    private final String title;

    private Book(BookBuilder bookbuilder) {
        this.isbn = bookbuilder.isbn;
        this.authorLastName = bookbuilder.authorLastName;
        this.authorFirstName = bookbuilder.authorFirstName;
        this.title = bookbuilder.title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getTitle() {
        return title;
    }

     //-----------------------------------------------------------------------------------------------------------------

    public static class BookBuilder {

        private String isbn;
        private String authorLastName;
        private String authorFirstName;
        private String title;

        private BookBuilder(){}

        public static BookBuilder bookBuilder(){
            return new BookBuilder();
        }

        Book build(){
            return new Book(this);
        }

        public BookBuilder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder withAuthorLastName(String authorLastName) {
            this.authorLastName = authorLastName;
            return this;
        }

        public BookBuilder withAuthorFirstName(String authorFirstName) {
            this.authorFirstName = authorFirstName;
            return this;
        }

        public BookBuilder withTitle(String title) {
            this.title = title;
            return this;
        }
    }
}