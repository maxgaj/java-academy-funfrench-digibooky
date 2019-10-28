package be.mc.funfrench.digibooky.domain.users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Librarian implements BaseUser {

    private String id;
    private String password;
    private UserStatus status;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String email;

    public Librarian(String password, String firstName, String lastName, String email){
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = new ArrayList<>();
        this.status = UserStatus.LIBRARIAN;
        this.roles.add(UserStatus.LIBRARIAN.toString());
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public UserStatus getStatus() {
        return status;
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
}
