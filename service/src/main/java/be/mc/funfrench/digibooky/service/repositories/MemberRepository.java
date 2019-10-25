package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Member;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemberRepository {

    private final ConcurrentHashMap<String, Member> memberById;

    public MemberRepository() {
        this.memberById = new ConcurrentHashMap<>();
    }

    public Member persist(Member member) {
        this.memberById.put(member.getId(), member);
        return member;
    }

    public boolean isINSSUnique(String INSS) {
        long count = memberById.values().stream()
                .filter(member -> member.getInss().equals(INSS))
                .count();
        return count <= 0;
    }

    public boolean isEmailUnique(String email) {
        long count = memberById.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .count();
        return count <= 0;
    }

    public boolean isIdUnique(String id) {
        long count = memberById.values().stream()
                .filter(member -> member.getId().equals(id))
                .count();
        return count <= 0;
    }


}
