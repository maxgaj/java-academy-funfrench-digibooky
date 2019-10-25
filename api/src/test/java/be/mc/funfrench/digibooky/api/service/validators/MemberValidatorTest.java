package be.mc.funfrench.digibooky.api.service.validators;

import be.mc.funfrench.digibooky.domain.Member;
import be.mc.funfrench.digibooky.service.repositories.MemberRepository;
import be.mc.funfrench.digibooky.service.repositories.MemberValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberValidatorTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberValidator memberValidator;

    @Test
    void validate_givenCorrectMember_ThenReturnTrue() {
        Mockito.when(memberRepository.isEmailUnique("honsenfou@gmail.com")).thenReturn(true);
        Mockito.when(memberRepository.isINSSUnique("15348352")).thenReturn(true);
        Member member = Member.MemberBuilder.memberBuilder()
                .withLastname("Senfou")
                .withFirstname("Hon")
                .withINSS("15348352")
                .withEmail("honsenfou@gmail.com")
                .withStreetName("oijdsf")
                .withStreetNumber(12)
                .withPostalCode(4000)
                .withCity("Liège")
                .build();
        Mockito.when(memberRepository.isIdUnique(member.getId())).thenReturn(true);
        boolean result = memberValidator.validate(member);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void validate_GivenEmptyLastname_ThenExceptionThrown() {
        Member member = Member.MemberBuilder.memberBuilder()
                .withLastname("")
                .withFirstname("Hon")
                .withINSS("15348352")
                .withEmail("honsenfou@gmail.com")
                .withStreetName("oijdsf")
                .withStreetNumber(12)
                .withPostalCode(4000)
                .withCity("Liège")
                .build();
        Assertions.assertThatThrownBy(() -> memberValidator.validate(member)).hasMessage("Member should have a last name");
    }

    @Test
    void validate_GivenEmptyCity_ThenExceptionThrown() {
        Member member = Member.MemberBuilder.memberBuilder()
                .withLastname("Senfou")
                .withFirstname("Hon")
                .withINSS("15348352")
                .withEmail("honsenfou@gmail.com")
                .withStreetName("oijdsf")
                .withStreetNumber(12)
                .withPostalCode(4000)
                .withCity("")
                .build();
        Assertions.assertThatThrownBy(() -> memberValidator.validate(member)).hasMessage("Member should have a city");
    }

    @Test
    void validate_GivenInvalidEmail_ThenExceptionThrown() {
        Member member = Member.MemberBuilder.memberBuilder()
                .withLastname("Senfou")
                .withFirstname("Hon")
                .withINSS("15348352")
                .withEmail("honsenfou@gmailcom")
                .withStreetName("oijdsf")
                .withStreetNumber(12)
                .withPostalCode(4000)
                .withCity("Liège")
                .build();
        Assertions.assertThatThrownBy(() -> memberValidator.validate(member)).hasMessage("Email should be formatted as x@x.x");
    }
}