package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.Member;
import be.mc.funfrench.digibooky.domain.users.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class MemberRepository {

    private final BaseUserRepository repository;

    @Autowired
    public MemberRepository(BaseUserRepository repository) {
        this.repository = repository;
    }

    public Collection<Member> findAll(){
        return repository.findAllByStatus(UserStatus.MEMBER).stream()
                .map(member -> (Member) member)
                .collect(Collectors.toList());
    }

    public Member persist(Member member) {
        return (Member) repository.persist(member);
    }

    public long countByInss(String inss){
        return findAll().stream()
                .filter(member -> member.getInss().equals(inss))
                .count();
    }

    public long countByEmail(String email){
        return findAll().stream()
                .filter(member -> member.getEmail().equals(email))
                .count();
    }

    public long countById(String id){
        return findAll().stream()
                .filter(member -> member.getId().equals(id))
                .count();
    }

    public Member findOneOrNullById(String id) {
        return findAll().stream().filter(member -> member.getId().equals(id)).findFirst().orElse(null);
    }
}
