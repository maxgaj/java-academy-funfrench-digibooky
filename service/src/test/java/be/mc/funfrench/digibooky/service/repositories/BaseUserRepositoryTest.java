package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.BaseUser;
import be.mc.funfrench.digibooky.domain.users.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BaseUserRepositoryTest {

    @Test
    void findAll_givenNewRepository_thenReturnCollectionWithDefaultAdmin() {
        BaseUserRepository repository = new BaseUserRepository();
        Collection<BaseUser> users = repository.findAll();
        Assertions.assertThat(users).isNotEmpty();
        Assertions.assertThat(users.size()).isEqualTo(1);
    }

    @Test
    void persist_givenNewMember_thenMemberHasId() {
        BaseUser member = Member.MemberBuilder.memberBuilder().build();
        BaseUserRepository repository = new BaseUserRepository();
        BaseUser savedMember = repository.persist(member);
        Assertions.assertThat(savedMember).isEqualTo(member);
        Assertions.assertThat(savedMember.getId()).isNotNull();
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
    void findAllByStatus_givenRepositoryWithUsers_thenReturnCorrectUsers() {
        Member member = Member.MemberBuilder.memberBuilder().build();
        BaseUserRepository repository = new BaseUserRepository();
        repository.persist(member);
        Assertions.assertThat(repository.findAllByStatus(member.getStatus())).containsExactly(member);
    }

    @Test
    void findOneByIdAndPassword_givenRepositoryWithUser_thenReturnCorrectUser() {
        Member member = Member.MemberBuilder.memberBuilder()
                .withPassword("test")
                .build();
        BaseUserRepository repository = new BaseUserRepository();
        BaseUser savedUser = repository.persist(member);
        Assertions.assertThat(repository.findOneByIdAndPassword(savedUser.getId(), savedUser.getPassword())).isEqualTo(savedUser);
    }

    @Test
    void findOneByIdAndPassword_givenEmptyRepository_thenReturnNull() {
        BaseUserRepository repository = new BaseUserRepository();
        Assertions.assertThat(repository.findOneByIdAndPassword("test", "test")).isNull();
    }
}