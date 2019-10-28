package be.mc.funfrench.digibooky.api.dtos;

import be.mc.funfrench.digibooky.domain.users.UserStatus;

import java.util.List;

public class LibrarianDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public String getId() {
        return id;
    }

    public LibrarianDto withId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public LibrarianDto withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public LibrarianDto withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LibrarianDto withEmail(String email) {
        this.email = email;
        return this;
    }
}
