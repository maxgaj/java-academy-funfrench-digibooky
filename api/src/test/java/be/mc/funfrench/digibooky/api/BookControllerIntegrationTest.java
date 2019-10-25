package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.BookDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestApplication.class)
class BookControllerIntegrationTest {

    private final int PORT = 8080;

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
}