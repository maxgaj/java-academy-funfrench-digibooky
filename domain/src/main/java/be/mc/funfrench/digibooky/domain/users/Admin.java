package be.mc.funfrench.digibooky.domain.users;

import java.util.ArrayList;
import java.util.List;

public class Admin implements BaseUser {
    private String id;
    private String password;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String email;

    public Admin(String password, String firstName, String lastName, String email) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = new ArrayList<>();
        this.roles.add("ADMIN");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }
}
