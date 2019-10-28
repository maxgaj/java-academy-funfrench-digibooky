package be.mc.funfrench.digibooky.api.dtos;

public class CreateLibrarianDto {
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public String getPassword() {
        return password;
    }

    public void withPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void withFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void withLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void withEmail(String email) {
        this.email = email;
    }
}
