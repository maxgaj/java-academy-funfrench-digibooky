package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.LibrarianDto;
import be.mc.funfrench.digibooky.api.dtos.MemberDto;
import be.mc.funfrench.digibooky.api.security.LibrarianController;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestApplication.class
)

class LibrarianControllerIntegrationTest {

    @Autowired
    private LibrarianController librarianController;
    private final int PORT = 8080;

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
}