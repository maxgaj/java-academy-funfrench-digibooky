package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class MemberValidator {
    private MemberRepository memberRepository;

    @Autowired
    public MemberValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean validate(Member member) {
        hasLastName(member);
        hasCity(member);
        hasValidEmail(member);
        hasUniqueEmail(member);
        hasUniqueId(member);
        hasUniqueINSS(member);
        return true;
    }

    private void hasValidEmail(Member member) {
        String regex = "^(.+)@(.+)\\.(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(member.getEmail()).matches()){
            throw new IllegalArgumentException("Email should be formatted as x@x.x");
        }
    }

    private void hasCity(Member member) {
        if (member.getLastname()==null || member.getCity().isEmpty()){
            throw new IllegalArgumentException("Member should have a city");
        }
    }
    
    private void hasLastName(Member member){
        if (member.getLastname()==null || member.getLastname().isEmpty()){
            throw new IllegalArgumentException("Member should have a last name");
        }
    }
    
    private void hasUniqueINSS(Member member){
        if (!memberRepository.isINSSUnique(member.getInss())){
            throw new IllegalArgumentException("The INSS should be unique");
        }
    }

    private void hasUniqueId(Member member){
        if (!memberRepository.isIdUnique(member.getId())){
            throw new IllegalArgumentException("The ID should be unique");
        }
    }

    private void hasUniqueEmail(Member member){
        if (!memberRepository.isEmailUnique(member.getEmail())){
            throw new IllegalArgumentException("The email should be unique");
        }
    }
}
