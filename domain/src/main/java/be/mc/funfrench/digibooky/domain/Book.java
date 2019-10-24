package be.mc.funfrench.digibooky.domain;

public class Book {

    private final String isbn13;
    private final String authorLastName;
    private final String authorFirstName;
    private final String title;

    private Book(BookBuilder bookbuilder) {
        this.isbn13 = bookbuilder.isbn13;
        this.authorLastName = bookbuilder.authorLastName;
        this.authorFirstName = bookbuilder.authorFirstName;
        this.title = bookbuilder.title;
    }

    public String getIsbn13() {
        return isbn13;
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

        private String isbn13;
        private String authorLastName;
        private String authorFirstName;
        private String title;

        public BookBuilder(){}

        public static BookBuilder bookBuilder(){
            return new BookBuilder();
        }

        public Book build(){
            return new Book(this);
        }

        public BookBuilder withIsbn13(String isbn13) {
            this.isbn13 = isbn13;
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
