package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.BaseUser;
import be.mc.funfrench.digibooky.domain.users.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MemberRepositoryTest {
    @Mock
    private BaseUserRepository baseUserRepository;
    @InjectMocks
    private MemberRepository memberRepository;

    @Test
    void findAll_givenNewRepository_thenReturnEmptyCollection() {
        Collection<Member> users = memberRepository.findAll();
        Assertions.assertThat(users).isEmpty();
    }

    @Test
    void persist_givenNewMember_thenMemberIsReturned() {
        Member member = Member.MemberBuilder.memberBuilder().build();
        Mockito.when(baseUserRepository.persist(member)).thenReturn(member);
        BaseUser savedMember = memberRepository.persist(member);
        Assertions.assertThat(savedMember).isEqualTo(member);
    }

    @Test
    void persist_givenNewMember_thenMemberIsPersisted() {
        BaseUser member = Member.MemberBuilder.memberBuilder().build();
        BaseUserRepository repository = new BaseUserRepository();
        int size = repository.findAll().size();
        repository.persist(member);
        Assertions.assertThat(repository.findAll().size()).isEqualTo(size+1);
    }

    @Test
    void findAll_givenRepositoryWithMember_thenReturnNonEmptyCollection() {
        Member member = Member.MemberBuilder.memberBuilder().build();
        Mockito.when(baseUserRepository.persist(member)).thenReturn(member);
        List<BaseUser> members = new ArrayList<>();
        members.add(member);
        Mockito.when(baseUserRepository.findAllByStatus(member.getStatus())).thenReturn(members);
        memberRepository.persist(member);
        Collection<Member> users = memberRepository.findAll();
        Assertions.assertThat(users).containsExactly(member);
    }

    @Test
    void countByInss_givenEmptyDb_thenReturnZero(){
        Assertions.assertThat(memberRepository.countByInss("TEST")).isEqualTo(0);
    }

    @Test
    void countByInss_givenNonEmptyDb_thenReturnCorrectCount(){
        String inss = "Test";
        Member member = Member.MemberBuilder.memberBuilder().withInss(inss).build();
        Mockito.when(baseUserRepository.persist(member)).thenReturn(member);
        List<BaseUser> members = new ArrayList<>();
        members.add(member);
        Mockito.when(baseUserRepository.findAllByStatus(member.getStatus())).thenReturn(members);
        memberRepository.persist(member);
        Assertions.assertThat(memberRepository.countByInss(inss)).isEqualTo(1);
    }

    @Test
    void countById_givenEmptyDb_thenReturnZero(){
        Assertions.assertThat(memberRepository.countById("TEST")).isEqualTo(0);
    }

    @Test
    void countById_givenNonEmptyDb_thenReturnCorrectCount(){
        Member member = Member.MemberBuilder.memberBuilder().build();
        member.setId("TEST");
        Mockito.when(baseUserRepository.persist(member)).thenReturn(member);
        List<BaseUser> members = new ArrayList<>();
        members.add(member);
        Mockito.when(baseUserRepository.findAllByStatus(member.getStatus())).thenReturn(members);
        memberRepository.persist(member);
        Assertions.assertThat(memberRepository.countById("TEST")).isEqualTo(1);
    }

    @Test
    void countByEmail_givenEmptyDb_thenReturnZero(){
        Assertions.assertThat(memberRepository.countByEmail("TEST")).isEqualTo(0);
    }

    @Test
    void countByEmail_givenNonEmptyDb_thenReturnCorrectCount(){
        String email = "Test";
        Member member = Member.MemberBuilder.memberBuilder().withEmail(email).build();
        Mockito.when(baseUserRepository.persist(member)).thenReturn(member);
        List<BaseUser> members = new ArrayList<>();
        members.add(member);
        Mockito.when(baseUserRepository.findAllByStatus(member.getStatus())).thenReturn(members);
        memberRepository.persist(member);
        Assertions.assertThat(memberRepository.countByEmail(email)).isEqualTo(1);
    }
}