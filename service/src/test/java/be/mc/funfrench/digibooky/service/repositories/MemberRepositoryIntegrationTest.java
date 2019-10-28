package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.BaseUser;
import be.mc.funfrench.digibooky.domain.users.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryIntegrationTest {

    @Test
    void givenEmptyRepository_whenPersistMembers_thenMemberHadIs() {
        BaseUserRepository baseUserRepository = new BaseUserRepository();
        MemberRepository memberRepository = new MemberRepository(baseUserRepository);
        Member member = Member.MemberBuilder.memberBuilder().build();
        Member savedMember = memberRepository.persist(member);
        Assertions.assertThat(savedMember.getId()).isNotNull();
    }

    @Test
    void givenNonEmptyRepository_whenFindAll_thenReturnCorrectCollection() {
        BaseUserRepository baseUserRepository = new BaseUserRepository();
        MemberRepository memberRepository = new MemberRepository(baseUserRepository);
        Member member1 = Member.MemberBuilder.memberBuilder().build();
        memberRepository.persist(member1);
        Member member2 = Member.MemberBuilder.memberBuilder().build();
        memberRepository.persist(member2);
        Assertions.assertThat(memberRepository.findAll()).containsExactlyInAnyOrder(member1, member2);
    }

    @Test
    void givenNonEmptyRepository_whenCountBy_thenReturnCorrectCount() {
        BaseUserRepository baseUserRepository = new BaseUserRepository();
        MemberRepository memberRepository = new MemberRepository(baseUserRepository);
        Member member = Member.MemberBuilder.memberBuilder()
                .withInss("inss")
                .withEmail("email")
                .build();
        Member savedMember = memberRepository.persist(member);
        Assertions.assertThat(memberRepository.countByEmail("email")).isEqualTo(1);
        Assertions.assertThat(memberRepository.countByInss("inss")).isEqualTo(1);
        Assertions.assertThat(memberRepository.countById(savedMember.getId())).isEqualTo(1);
    }
}