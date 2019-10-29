package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import be.mc.funfrench.digibooky.api.dtos.CreateBookDto;
import be.mc.funfrench.digibooky.api.dtos.CreateMemberDto;
import be.mc.funfrench.digibooky.api.mappers.BookMapper;
import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import be.mc.funfrench.digibooky.service.validators.BookValidator;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TestApplication.class)
class BookControllerIntegrationTest {

    @LocalServerPort
    private int PORT;

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

//    @Test
//    void registerNewBook_WhenGivenCreateDto_ThenPersistBookIntoRepository() {
//        String payload = "{\n" +
//                "\"isbn13\": \"12-12121-12-1\",\n" +
//                "\t\"authorLastName\": \"Orwell\",\n" +
//                "\t\"title\": \"1984\",\n" +
//                "}";
//
//        Book bookReturned =
//                RestAssured
//                        .given()
//                        .baseUri("http://localhost")
//                        .header("Content-Type", "application/json")
//                        .port(PORT)
//                        .body(payload)
//                        .when()
//                        .post("/books")
//                        .then()
//                        .assertThat()
//                        .statusCode(HttpStatus.CREATED.value())
//                        .extract().as(Book.class);
//
//        System.out.println(bookReturned.getAuthorLastName());
//        assertThat(bookReturned.getAuthorLastName().equals("Orwell"));
//    } //TODO FINISH THE TEST
//
//    @Test
//    void checkIsbnFormat_GivenDatasToCreateBook_WhenGivingIsbn_ThenCheckIsbnFormat() {
//        String payload = "{\n" +
//                "\"isbn13\": \"WRONGISBN\",\n" +
//                "\t\"authorLastName\": \"Orwell\",\n" +
//                "\t\"title\": \"1984\",\n" +
//                "}";
//
//        Book book = RestAssured
//                .given()
//                .baseUri("http://localhost")
//                .header("Content-Type", "application/json")
//                .port(PORT)
//                .body(payload)
//                .when()
//                .post("/books")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.BAD_REQUEST.value())
//                .extract().as(Book.class);
//
//        //TODO assertThrows exception
//    }
}
