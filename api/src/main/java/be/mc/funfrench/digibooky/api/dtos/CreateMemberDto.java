package be.mc.funfrench.digibooky.api.dtos;

public class CreateMemberDto {
    private String inss;
    private String lastname;
    private String firstname;
    private String email;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;

    public String getInss() {
        return inss;
    }

    public CreateMemberDto withInss(String INSS) {
        this.inss = INSS;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public CreateMemberDto withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public CreateMemberDto withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateMemberDto withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public CreateMemberDto withStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public CreateMemberDto withStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public CreateMemberDto withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CreateMemberDto withCity(String city) {
        this.city = city;
        return this;
    }
}
