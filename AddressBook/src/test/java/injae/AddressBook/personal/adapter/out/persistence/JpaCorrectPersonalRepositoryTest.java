package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.CorrectPersonalRepository;
import injae.AddressBook.personal.application.port.out.GetPersonalRepository;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaCorrectPersonalRepository.class, JpaGetPersonalRepository.class,
        JpaRecordPersonalRepository.class})
@Transactional
class JpaCorrectPersonalRepositoryTest {

    @Autowired
    CorrectPersonalRepository correctRepository;

    @Autowired
    GetPersonalRepository getRepository;

    @Autowired
    RecordPersonalRepository recordRepository;

    @Test
    void updateTest() {
        //given
        Personal original = new Personal(
                "봉길동",
                "서울시 양천구",
                "023340593",
                "bong@gmail.com"
        );
        recordRepository.save(original);

        Long correctId = original.getId();
        original = getRepository.findOne(correctId);
        String originalAddress = original.getAddress();
        String originalTelephoneNumber = original.getTelephoneNumber();
        String originalEmailAddress = original.getEmailAddress();

        Personal updatePersonal = new Personal(
                correctId,
                original.getName(),
                "서울시 은평구",
                "024439034",
                originalEmailAddress
        );

        //when
        correctRepository.update(updatePersonal);
        updatePersonal = getRepository.findOne(correctId);

        //then
        assertThat(updatePersonal.getAddress()).isNotEqualTo(originalAddress);
        assertThat(updatePersonal.getAddress()).isEqualTo("서울시 은평구");
        assertThat(updatePersonal.getTelephoneNumber()).isNotEqualTo(originalTelephoneNumber);
        assertThat(updatePersonal.getTelephoneNumber()).isEqualTo("024439034");
    }
}
