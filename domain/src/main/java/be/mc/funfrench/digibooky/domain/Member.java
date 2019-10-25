package be.mc.funfrench.digibooky.domain;

import java.util.UUID;

public class Member {
    private UUID id;
    private String inss;
    private String lastname;
    private String firstname;
    private String email;
    private String streetName;
    private int streetNumber;
    private int postalCode;
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

    public int getStreetNumber() {
        return streetNumber;
    }

    public int getPostalCode() {
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
        private int streetNumber;
        private int postalCode;
        private String city;

        private MemberBuilder() {
        }

        public Member build() {
            return new Member(this);
        }

        public String getInss() {
            return inss;
        }

        public MemberBuilder withInss(String INSS) {
            this.inss = INSS;
            return this;
        }

        public String getLastname() {
            return lastname;
        }

        public MemberBuilder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public String getFirstname() {
            return firstname;
        }

        public MemberBuilder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public MemberBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public String getStreetName() {
            return streetName;
        }

        public MemberBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public int getStreetNumber() {
            return streetNumber;
        }

        public MemberBuilder withStreetNumber(int streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public int getPostalCode() {
            return postalCode;
        }

        public MemberBuilder withPostalCode(int postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public String getCity() {
            return city;
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
