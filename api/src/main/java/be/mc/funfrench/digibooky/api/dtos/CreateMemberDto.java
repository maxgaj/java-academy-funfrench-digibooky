package be.mc.funfrench.digibooky.api.dtos;

public class CreateMemberDto {
    private String INSS;
    private String lastname;
    private String firstname;
    private String email;
    private String streetName;
    private int streetNumber;
    private int postalCode;
    private String city;

    public String getINSS() {
        return INSS;
    }

    public CreateMemberDto withINSS(String INSS) {
        this.INSS = INSS;
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

    public int getStreetNumber() {
        return streetNumber;
    }

    public CreateMemberDto withStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public CreateMemberDto withPostalCode(int postalCode) {
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
