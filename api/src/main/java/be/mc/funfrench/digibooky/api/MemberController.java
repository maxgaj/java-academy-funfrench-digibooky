package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.api.dtos.CreateMemberDto;
import be.mc.funfrench.digibooky.api.dtos.MemberDto;
import be.mc.funfrench.digibooky.api.mappers.MemberMapper;
import be.mc.funfrench.digibooky.domain.Member;
import be.mc.funfrench.digibooky.service.repositories.MemberRepository;
import be.mc.funfrench.digibooky.service.validators.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = MemberController.MEMBER_CONTROLLER_RESOURCE_URL)
public class MemberController {
    public static final String MEMBER_CONTROLLER_RESOURCE_URL = "/members";
    private MemberRepository memberRepository;
    private MemberMapper memberMapper;
    private MemberValidator memberValidator;

    @Autowired
    public MemberController(MemberRepository memberRepository, MemberMapper memberMapper, MemberValidator memberValidator) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.memberValidator = memberValidator;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto registerMember(@RequestBody CreateMemberDto createMemberDto) {
        Member memberToCreate= memberMapper.mapToNewDomain(createMemberDto);
        memberValidator.validate(memberToCreate);
        return memberMapper.mapToDto(
                memberRepository.persist(
                        memberToCreate
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected void invalidMemberRegistrationException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
