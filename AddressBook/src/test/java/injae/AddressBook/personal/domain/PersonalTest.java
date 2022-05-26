package injae.AddressBook.personal.domain;

import injae.AddressBook.common.PersonalTestData;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonalTest {

    @Test
    void changePersonalInfoTest() {
        Personal personal = PersonalTestData.defaultPersonal().build();

        Personal changePersonal = PersonalTestData.defaultPersonal()
                .withAddress("서울시 서초구")
                .withTelephoneNumber("023320975")
                .build();

        personal.changePersonalInfo(changePersonal);

        assertThat(personal.getAddress()).isEqualTo(changePersonal.getAddress());
        assertThat(personal.getTelephoneNumber())
                .isEqualTo(changePersonal.getTelephoneNumber());
    }

}
