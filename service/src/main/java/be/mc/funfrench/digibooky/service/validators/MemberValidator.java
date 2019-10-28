package be.mc.funfrench.digibooky.service.validators;

import be.mc.funfrench.digibooky.domain.users.Member;
import be.mc.funfrench.digibooky.service.repositories.MemberRepository;
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
        hasInss(member);
        hasEmail(member);
        hasValidEmail(member);
        hasUniqueEmail(member);
        hasUniqueId(member);
        hasUniqueInss(member);
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

    private void hasInss(Member member){
        if (member.getInss()==null || member.getInss().isEmpty()){
            throw new IllegalArgumentException("Member should have an INSS number");
        }
    }

    private void hasEmail(Member member){
        if (member.getEmail()==null || member.getEmail().isEmpty()){
            throw new IllegalArgumentException("Member should have an INSS number");
        }
    }
    
    private void hasUniqueInss(Member member){
        if (memberRepository.countByInss(member.getInss()) > 0){
            throw new IllegalArgumentException("The INSS should be unique");
        }
    }

    private void hasUniqueId(Member member){
        if (memberRepository.countById(member.getId()) > 0){
            throw new IllegalArgumentException("The ID should be unique");
        }
    }

    private void hasUniqueEmail(Member member){
        if (memberRepository.countByEmail(member.getEmail()) > 0){
            throw new IllegalArgumentException("The email should be unique");
        }
    }
}
