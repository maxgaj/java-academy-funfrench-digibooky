package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.LendingDto;
import be.mc.funfrench.digibooky.api.dtos.MemberDto;
import be.mc.funfrench.digibooky.api.dtos.ReturnLendingDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TestApplication.class)

class LendingControllerIntegrationTest {

    @LocalServerPort
    private int PORT;

    @Test
    void givenEmptyRepository_whenAddLendingAndDeleteIt_thenTheDeletedOneIsReturned() {

       /* String bookIsbn = "12-12345-34-5";

        String payload = "{\n" +
                "\"firstname\": \"firstname\",\n" +
                "\t\"lastname\": \"lastname\",\n" +
                "\t\"password\": \"password\",\n" +
                "\t\"inss\": \"1\",\n" +
                "\t\"email\": \"maxime.gaj@gmail.com\",\n" +
                "\t\"streetName\": \"jio\",\n" +
                "\t\"streetNumber\": \"12\",\n" +
                "\t\"postalCode\": \"4000\",\n" +
                "\t\"city\": \"Liège\""+
                "}";

        MemberDto memberDto = RestAssured
                .given()
                .baseUri("http://localhost")
                .header("Content-Type", "application/json")
                .port(PORT)
                .body(payload)
                .when()
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(MemberDto.class);

        LendingDto createdLending =
                RestAssured
                        .given()
                        .baseUri("http://localhost")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Basic dXNlcjA6YWRtaW4=")
                        .accept("application/json")
                        .when()
                        .port(PORT)
                        .post("/lendings/book/" + bookIsbn + "/user/" + memberDto.getId())
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract().as(LendingDto.class);

        ReturnLendingDto deletedLending =
                RestAssured
                        .given()
                        .baseUri("http://localhost")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Basic dXNlcjA6YWRtaW4=")
                        .accept("application/json")
                        .when()
                        .port(PORT)
                        .post("/lendings/" + createdLending.getId())
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract().as(ReturnLendingDto.class);

        Assertions.assertEquals(createdLending.getId(), deletedLending.getId());
        RestAssured
                .given()
                .baseUri("http://localhost")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic dXNlcjA6YWRtaW4=")
                .accept("application/json")
                .when()
                .port(PORT)
                .post("/lendings/" + createdLending.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());*/
    }
}