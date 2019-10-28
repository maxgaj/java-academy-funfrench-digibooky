package be.mc.funfrench.digibooky.api;

import be.mc.funfrench.digibooky.domain.users.Librarian;
import be.mc.funfrench.digibooky.service.repositories.BaseUserRepository;
import be.mc.funfrench.digibooky.service.repositories.LibrarianRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "be.mc.funfrench.digibooky")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

//    @Bean("testLibrarianRepo")
//    public static LibrarianRepository getTestLibrarianController(){
//        Librarian librarian = new Librarian("1234", "Dany", "Van Praet", "dany@gmail.com");
//        BaseUserRepository baseUserRepository = new BaseUserRepository();
//        baseUserRepository.persist(librarian);
//
//        return new LibrarianRepository(baseUserRepository);
//    }
}