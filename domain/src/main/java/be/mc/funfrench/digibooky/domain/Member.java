package be.mc.funfrench.digibooky.domain;

import java.util.UUID;

public class Member {
    private UUID id;
    private String inss;
    private String lastname;
    private String firstname;
    private String email;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;

    private Member(MemberBuilder memberBuilder) {
        this.id = UUID.randomUUID();
        this.inss = memberBuilder.inss;
        this.lastname = memberBuilder.lastname;
        this.firstname = memberBuilder.firstname;
        this.email = memberBuilder.email;
        this.streetName = memberBuilder.streetName;
        this.streetNumber = memberBuilder.streetNumber;
        this.postalCode = memberBuilder.postalCode;
        this.city = memberBuilder.city;
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

    public String getId() {
        return this.id.toString();
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

        public static MemberBuilder memberBuilder() {
            return new MemberBuilder();
        }
    }


}
