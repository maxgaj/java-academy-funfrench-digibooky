package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.CreateMemberDto;
import be.mc.funfrench.digibooky.api.dtos.MemberDto;
import be.mc.funfrench.digibooky.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDto mapToDto(Member member) {
        return new MemberDto().withFirstname(member.getFirstname())
                .withLastname(member.getLastname())
                .withInss(member.getInss())
                .withEmail(member.getEmail())
                .withId(member.getId())
                .withStreetName(member.getStreetName())
                .withStreetNumber(member.getStreetNumber())
                .withPostalCode(member.getPostalCode())
                .withCity(member.getCity());
    }

    public Member mapToNewDomain(CreateMemberDto createMemberDto) {
        return Member.MemberBuilder.memberBuilder()
                .withLastname(createMemberDto.getLastname())
                .withFirstname(createMemberDto.getFirstname())
                .withInss(createMemberDto.getInss())
                .withEmail(createMemberDto.getEmail())
                .withStreetName(createMemberDto.getStreetName())
                .withStreetNumber(createMemberDto.getStreetNumber())
                .withPostalCode(createMemberDto.getPostalCode())
                .withCity(createMemberDto.getCity())
                .build();
    }
}
