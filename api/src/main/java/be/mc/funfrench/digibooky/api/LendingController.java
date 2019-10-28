package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import be.mc.funfrench.digibooky.service.repositories.MemberRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Lending Resource")
@RestController
@RequestMapping(path = LendingController.LENDING_CONTROLLER_RESOURCE_URL)
public class LendingController {

    public static final String LENDING_CONTROLLER_RESOURCE_URL = "/lendings";
    private MemberRepository memberRepository;
    private BookRepository bookRepository;

    @Autowired
    public LendingController(MemberRepository memberRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    
}
