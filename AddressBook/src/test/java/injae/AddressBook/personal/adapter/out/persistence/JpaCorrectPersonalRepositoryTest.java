package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.CorrectPersonalRepository;
import injae.AddressBook.personal.application.port.out.GetPersonalRepository;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class JpaCorrectPersonalRepositoryTest {

    @Autowired
    CorrectPersonalRepository correctRepository;

    @Autowired
    GetPersonalRepository getRepository;

    private final Long CORRECT_ID = 10000L;

    @Test
    @Sql("PersonalPersistenceAdapterTest.sql")//given
    void update_test() {
        //given
        Personal original = getRepository.findOne(CORRECT_ID);
        String originalAddress = original.getAddress();
        String originalTelephoneNumber = original.getTelephoneNumber();
        String originalEmailAddress = original.getEmailAddress();

        Personal updatePersonal = new Personal(
                original.getId(),
                original.getName(),
                originalAddress,
                originalTelephoneNumber,
                originalEmailAddress
        );

        //when
        correctRepository.update(updatePersonal);

        //then
        assertThat(getRepository.findOne(CORRECT_ID)).isEqualTo(original);

    }
}
