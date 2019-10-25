package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.CreateMemberDto;
import be.mc.funfrench.digibooky.api.dtos.MemberDto;
import be.mc.funfrench.digibooky.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberMapperTest {

    @Test
    void mapToDto_givenMember_thenReturnCorrectDto() {
        //GIVEN
        Member member = Member.MemberBuilder.memberBuilder()
                .withLastname("Senfou")
                .withFirstname("Hon")
                .withInss("15348352")
                .withEmail("honsenfou@gmail.com")
                .withStreetName("oijdsf")
                .withStreetNumber("12")
                .withPostalCode("4000")
                .withCity("Liège")
                .build();
        MemberMapper memberMapper = new MemberMapper();
        //WHEN
        MemberDto memberDto = memberMapper.mapToDto(member);
        //THEN
        Assertions.assertThat(memberDto.getId()).isEqualTo(member.getId().toString());
        Assertions.assertThat(memberDto.getFirstname()).isEqualTo(member.getFirstname());
        Assertions.assertThat(memberDto.getLastname()).isEqualTo(member.getLastname());
        Assertions.assertThat(memberDto.getInss()).isEqualTo(member.getInss());
        Assertions.assertThat(memberDto.getEmail()).isEqualTo(member.getEmail());
        Assertions.assertThat(memberDto.getStreetName()).isEqualTo(member.getStreetName());
        Assertions.assertThat(memberDto.getCity()).isEqualTo(member.getCity());
        Assertions.assertThat(memberDto.getStreetNumber()).isEqualTo(member.getStreetNumber());
        Assertions.assertThat(memberDto.getPostalCode()).isEqualTo(member.getPostalCode());
    }

    @Test
    void mapToNewDomain_givenCreateMemberDto_returnCorrectMember() {
        //GIVEN
        CreateMemberDto createMemberDto = new CreateMemberDto()
                .withLastname("Senfou")
                .withFirstname("Hon")
                .withInss("15348352")
                .withEmail("honsenfou@gmail.com")
                .withStreetName("oijdsf")
                .withStreetNumber("12")
                .withPostalCode("4000")
                .withCity("Liège");
        MemberMapper memberMapper = new MemberMapper();
        //WHEN
        Member member = memberMapper.mapToNewDomain(createMemberDto);
        //THEN
        Assertions.assertThat(member.getId()).isNotNull();
        Assertions.assertThat(member.getFirstname()).isEqualTo(createMemberDto.getFirstname());
        Assertions.assertThat(member.getLastname()).isEqualTo(createMemberDto.getLastname());
        Assertions.assertThat(member.getInss()).isEqualTo(createMemberDto.getInss());
        Assertions.assertThat(member.getEmail()).isEqualTo(createMemberDto.getEmail());
        Assertions.assertThat(member.getStreetName()).isEqualTo(createMemberDto.getStreetName());
        Assertions.assertThat(member.getCity()).isEqualTo(createMemberDto.getCity());
        Assertions.assertThat(member.getStreetNumber()).isEqualTo(createMemberDto.getStreetNumber());
        Assertions.assertThat(member.getPostalCode()).isEqualTo(createMemberDto.getPostalCode());
    }
}