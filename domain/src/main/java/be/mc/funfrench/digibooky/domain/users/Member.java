package be.mc.funfrench.digibooky.domain.users;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Member implements BaseUser {
    private String id;
    private String password;
    private UserStatus status;
    private List<String> roles;
    private String inss;
    private String lastname;
    private String firstname;
    private String email;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;

    private Member(MemberBuilder memberBuilder) {
        this.id = UUID.randomUUID().toString();
        this.inss = memberBuilder.inss;
        this.lastname = memberBuilder.lastname;
        this.firstname = memberBuilder.firstname;
        this.email = memberBuilder.email;
        this.streetName = memberBuilder.streetName;
        this.streetNumber = memberBuilder.streetNumber;
        this.postalCode = memberBuilder.postalCode;
        this.city = memberBuilder.city;
        this.password = memberBuilder.password;
        this.roles = new ArrayList<>();
        this.status = UserStatus.MEMBER;
        this.roles.add(UserStatus.MEMBER.toString());
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

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getInss() {
        return this.inss;
    }

    public String getEmail() {
        return this.email;
    }

    public static class MemberBuilder {
        private String inss;
        private String lastname;
        private String firstname;
        private String email;
        private String streetName;
        private String streetNumber;
        private String postalCode;
        private String city;
        private String password;

        private MemberBuilder() {
        }

        public Member build() {
            return new Member(this);
        }

        public MemberBuilder withInss(String INSS) {
            this.inss = INSS;
            return this;
        }

        public MemberBuilder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public MemberBuilder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public MemberBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public MemberBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public MemberBuilder withStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public MemberBuilder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public MemberBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public MemberBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public static MemberBuilder memberBuilder() {
            return new MemberBuilder();
        }
    }
}
