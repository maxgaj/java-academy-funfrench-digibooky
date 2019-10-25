package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.domain.Member;
import be.mc.funfrench.digibooky.service.repositories.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {
    @Test
    void persist_givenMember_returnCorrectMember() {
        //GIVEN
        Member member = Member.MemberBuilder.memberBuilder()
                .withLastname("Senfou")
                .withFirstname("Hon")
                .withInss("15348352")
                .withEmail("honsenfou@gmail.com")
                .withStreetName("oijdsf")
                .withStreetNumber(12)
                .withPostalCode(4000)
                .withCity("Liège")
                .build();
        MemberRepository repository = new MemberRepository();
        //WHEN
        Member savedMember = repository.persist(member);
        //THEN
        Assertions.assertThat(savedMember).isEqualTo(member);
    }

    @Test
    void givenMember_whenPersist_thenFieldsAreNotUnique() {
        //GIVEN
        Member member = Member.MemberBuilder.memberBuilder()
                .withLastname("Senfou")
                .withFirstname("Hon")
                .withInss("15348352")
                .withEmail("honsenfou@gmail.com")
                .withStreetName("oijdsf")
                .withStreetNumber(12)
                .withPostalCode(4000)
                .withCity("Liège")
                .build();
        MemberRepository repository = new MemberRepository();
        //WHEN
        repository.persist(member);
        //THEN
        Assertions.assertThat(repository.isEmailUnique(member.getEmail())).isFalse();
        Assertions.assertThat(repository.isIdUnique(member.getId())).isFalse();
        Assertions.assertThat(repository.isINSSUnique(member.getInss())).isFalse();
    }
}