package be.mc.funfrench.digibooky.api.dtos;

public class MemberDto {
    private String id;
    private String inss;
    private String lastname;
    private String firstname;
    private String email;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;

    public String getId() {
        return id;
    }

    public MemberDto withId(String id) {
        this.id = id;
        return this;
    }

    public String getInss() {
        return inss;
    }

    public MemberDto withInss(String INSS) {
        this.inss = INSS;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public MemberDto withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public MemberDto withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MemberDto withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public MemberDto withStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public MemberDto withStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public MemberDto withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public MemberDto withCity(String city) {
        this.city = city;
        return this;
    }

}
