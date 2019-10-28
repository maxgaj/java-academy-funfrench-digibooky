package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {TestApplication.class, BookRepository.class})
class BookControllerIntegrationTest {

    private final int PORT = 8080;
    private final BookRepository bookRepository;

    @Autowired
    public BookControllerIntegrationTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void getAllBooks_givenEmptyRepository_thenReturnNotEmptyListOfBooks() {
        BookDto[] booksReturned =
                RestAssured
                        .given()
                        .baseUri("http://localhost")
                        .accept("application/json")
                        .when()
                        .port(PORT)
                        .get("/books")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract().as(BookDto[].class);

        assertThat(booksReturned).isNotEmpty();  //TODO change it back to isEmpty after story 10A
    }

    @Test
    void getBooksByAuthorName_givenPreFilledRepository_whenAskBooksForAuthorThatNameStartsWithSa_thenReturnListOfBooksForSapkowskiAndSartre() {
        BookDto[] booksReturned =
                RestAssured
                        .given()
                            .baseUri("http://localhost")
                            .accept("application/json")
                        .when()
                            .port(PORT)
                            .get("/books?authorName=Sa*")
                        .then()
                            .assertThat()
                            .statusCode(HttpStatus.OK.value())
                            .extract().as(BookDto[].class);

        assertThat(Arrays.stream(booksReturned)
                .map(BookDto::getAuthorLastName)
                .collect(Collectors.toList()))
                .containsOnly("Sapkowski", "Sartre");

        assertThat(Arrays.stream(booksReturned)
                .map(BookDto::getTitle)
                .collect(Collectors.toList()))
                .containsOnly("The Withcer", "Les mains sales");
    }

    @Test
    void getBooksByTitle_whenGivenRightQueryParams_thenReturnNotEmptyListOfBooks() {
        BookDto[] booksReturned =
                RestAssured
                        .given()
                        .baseUri("http://localhost")
                        .accept("application/json")
                        .when()
                        .queryParam("title", "The*")
                        .port(PORT)
                        .get("/books")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract().as(BookDto[].class);

        assertThat(booksReturned).isNotEmpty();
    }

    @Test
    void getBooksByTitle_whenGivenWrongQueryParams_thenReturnEmptyListOfBooks() {
        BookDto[] booksReturned =
                RestAssured
                        .given()
                        .baseUri("http://localhost")
                        .accept("application/json")
                        .when()
                        .queryParam("title", "The")
                        .port(PORT)
                        .get("/books")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract().as(BookDto[].class);

        assertThat(booksReturned).isEmpty();
    }

    @Test
    void getBookById_whenGivenNotExistingId_thenStatusCodeIsBadRequest() {
        RestAssured
                .given()
                .baseUri("http://localhost")
                .accept("application/json")
                .when()
                .port(PORT)
                .get("/books/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getBookById_whenAskForExistingId_thenReturnsTheBookDtoOfTheBook() {
        String bookId = bookRepository.findAll().iterator().next().getId();
        BookDto bookReturned =
                RestAssured
                        .given()
                        .baseUri("http://localhost")
                        .accept("application/json")
                        .when()
                        .port(PORT)
                        .get("/books/" + bookId)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract().as(BookDto.class);

        assertThat(bookReturned.getId()).isEqualTo(bookId);
    }
}