package be.mc.funfrench.digibooky.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "be.mc.funfrench.digibooky")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
