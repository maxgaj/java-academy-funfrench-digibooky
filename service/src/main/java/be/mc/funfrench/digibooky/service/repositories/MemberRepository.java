package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.BaseUser;
import be.mc.funfrench.digibooky.domain.users.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemberRepository {

    private final BaseUserRepository repository;

    @Autowired
    public MemberRepository(BaseUserRepository repository) {
        this.repository = repository;
    }

    public Collection<Member> findAll(){
        return repository.findAllByRoles("MEMBER");
    }

    public Member persist(Member member) {
        return (Member) repository.persist(member);
    }

    public boolean isINSSUnique(String INSS) {
        long count = findAll().stream()
                .filter(member -> member.getInss().equals(INSS))
                .count();
        return count <= 0;
    }

    public boolean isEmailUnique(String email) {
        long count = findAll().stream()
                .filter(member -> member.getEmail().equals(email))
                .count();
        return count <= 0;
    }

    public boolean isIdUnique(String id) {
        long count = findAll().stream()
                .filter(member -> member.getId().equals(id))
                .count();
        return count <= 0;
    }


}
