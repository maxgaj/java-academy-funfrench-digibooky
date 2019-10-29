package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.LibrarianDto;
import be.mc.funfrench.digibooky.domain.users.Librarian;
import be.mc.funfrench.digibooky.service.repositories.BaseUserRepository;
import be.mc.funfrench.digibooky.service.repositories.LibrarianRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestApplication.class
)

class LibrarianControllerIntegrationTest {

    private final int PORT = 8080;
    @Autowired
    private ApplicationContext context;

    @Test
    void registerLibrarian_givenValidCreateMemberDto_whenRegisterLibrarian_thenStatusCodeCreated() {
        String payload = "{\n" +
                "\"password\": \"password\",\n" +
                "\"firstName\": \"firstname\",\n" +
                "\t\"lastName\": \"lastname\",\n" +
                "\t\"email\": \"maxime.gaj@gmail.com\""+
                "}";

        LibrarianDto librarianDto = RestAssured
                .given()
                .baseUri("http://localhost")
                .header("Content-Type", "application/json")
                .port(PORT)
                .body(payload)
                .when()
                .post("/librarians")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(LibrarianDto.class);

        Assertions.assertThat(librarianDto.getFirstName()).isEqualTo("firstname");
    }

    @Test
    void givenInvalidCredentials_whenGetAllLibrarians_thenReturnStatusCodeForbidden() {
        RestAssured
                .given()
                .baseUri("http://localhost")
                .header("Content-Type", "application/json")
                .port(PORT)
                .when()
                .get("/librarians")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void givenValidCredentials_whenGetAllLibrarians_thenReturnStatusCodeOk() {

        RestAssured
                .given()
                .baseUri("http://localhost")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic dXNlcjA6YWRtaW4=")
                .port(PORT)
                .when()
                .get("/librarians")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void givenASpringContext_whenStartingApplication_thenSeeWhatHappens() {

        Assertions.assertThat(context.containsBean("testLibrarianRepo")).isTrue();
    }
}