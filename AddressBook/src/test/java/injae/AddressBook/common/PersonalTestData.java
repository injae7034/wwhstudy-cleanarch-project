package injae.AddressBook.common;

import injae.AddressBook.personal.domain.Personal;

public class PersonalTestData {

    public static PersonalBuilder defaultPersonal() {
        return new PersonalBuilder()
//                .withId(1L)
                .withName("홍길동")
                .withAddress("서울시 중구")
                .withTelephoneNumber("023345934")
                .withEmailAddress("hong@naver.com");
    }

    public static class PersonalBuilder {

        private Long id;

        private String name;
        private String address;
        private String telephoneNumber;
        private String emailAddress;


        public PersonalBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PersonalBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonalBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonalBuilder withTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public PersonalBuilder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Personal build() {
            return new Personal(this.id, this.name, this.address,
                    this.telephoneNumber, this.emailAddress);
        }

    }

}
