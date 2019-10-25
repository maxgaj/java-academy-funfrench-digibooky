//package be.mc.funfrench.digibooky.api;//package be.mc.funfrench.digibooky.api;
//
//import be.mc.funfrench.digibooky.api.dtos.CreateMemberDto;
//import be.mc.funfrench.digibooky.api.dtos.MemberDto;
//import be.mc.funfrench.digibooky.api.mappers.MemberMapper;
//import be.mc.funfrench.digibooky.domain.Member;
//import be.mc.funfrench.digibooky.service.repositories.MemberRepository;
//import be.mc.funfrench.digibooky.service.repositories.MemberValidator;
//import be.mc.funfrench.digibooky.war.Application;
//import io.restassured.RestAssured;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
//        classes = Application.class)
//class MemberControllerIntegrationTest {
//
//    @Autowired
//    private MemberController memberController;
//    private final int PORT = 8080;

//    @Test
//    void givenValidCreateMemberDto_whenRegisterMember_thenStatusCodeCreated() {
//        String memberPayload = "{\n" +
//                "  \"firstname\": \"Hon\"\n" +
//                "  \"lastname\": \"Senfou\"\n" +
//                "  \"INSS\": \"15348352\"\n" +
//                "  \"email\": \"honsenfou@gmail.com\"\n" +
//                "  \"streetName\": \"oijdsf\"\n" +
//                "  \"streetNumber\": \"12\"\n" +
//                "  \"postalCode\": \"4000\"\n" +
//                "  \"city\": \"Liège\"\n" +
//                "}";
//
//        RestAssured
//                .given()
//                .baseUri("http://localhost")
//                .header("Content-Type", "application/json")
//                .port(PORT)
//                .body(memberPayload)
//                .when()
//                .port(PORT)
//                .post("/members")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.CREATED.value());
//    }
//
//    @Test
//    void givenCreateMemberDto_whenRegisterValidMember_returnCorrectDto() {
//        //GIVEN
//        CreateMemberDto createMemberDto = new CreateMemberDto()
//                .withLastname("Senfou")
//                .withFirstname("Hon")
//                .withINSS("15348352")
//                .withEmail("honsenfou@gmail.com")
//                .withStreetName("oijdsf")
//                .withStreetNumber(12)
//                .withPostalCode(4000)
//                .withCity("Liège");
//        //WHEN
//        MemberDto memberDto = memberController.registerMember(createMemberDto);
//        //THEN
//        Assertions.assertThat(memberDto.getINSS()).isEqualTo(createMemberDto.getINSS());
//    }
//
//    @Test
//    void givenCreateMemberDtoWithEmptyCity_whenRegister_returnBadRequest() {
//        //GIVEN
//        CreateMemberDto createMemberDto = new CreateMemberDto()
//                .withLastname("Senfou")
//                .withFirstname("Hon")
//                .withINSS("15348352")
//                .withEmail("honsenfou@gmail.com")
//                .withStreetName("oijdsf")
//                .withStreetNumber(12)
//                .withPostalCode(4000)
//                .withCity("");
//        //WHEN
//        MemberDto memberDto = memberController.registerMember(createMemberDto);
//        //THEN
//        Assertions.assertThat(memberDto.getINSS()).isEqualTo(createMemberDto.getINSS());
//    }
//}