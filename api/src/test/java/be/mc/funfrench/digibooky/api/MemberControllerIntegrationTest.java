package be.mc.funfrench.digibooky.api;//package be.mc.funfrench.digibooky.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TestApplication.class)
class MemberControllerIntegrationTest {

    @Autowired
    private MemberController memberController;
    private final int PORT = 8080;

    @Test
    void givenValidCreateMemberDto_whenRegisterMember_thenStatusCodeCreated() {
        String payload = "{\n" +
                "\"firstname\": \"firstname\",\n" +
                "\t\"lastname\": \"lastname\",\n" +
                "\t\"inss\": \"1\",\n" +
                "\t\"email\": \"maxime.gaj@gmail.com\",\n" +
                "\t\"streetName\": \"jio\",\n" +
                "\t\"streetNumber\": 12,\n" +
                "\t\"postalCode\": 4000,\n" +
                "\t\"city\": \"Liège\""+
                "}";;

        RestAssured
                .given()
                .baseUri("http://localhost")
                .header("Content-Type", "application/json")
                .port(PORT)
                .body(payload)
                .when()
                .port(PORT)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }
}