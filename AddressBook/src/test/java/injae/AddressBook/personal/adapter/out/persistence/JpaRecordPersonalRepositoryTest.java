package injae.AddressBook.personal.adapter.out.persistence;

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
@Import({JpaRecordPersonalRepository.class, JpaGetPersonalRepository.class})
@Transactional
class JpaRecordPersonalRepositoryTest {

    @Autowired
    RecordPersonalRepository recordRepository;

    @Autowired
    GetPersonalRepository getRepository;

    @Test
    void saveTest() {
        //given
        Personal personal = new Personal("홍길동", "서울시 중구",
                "01033459484", "hong@naver.com");
        System.out.println(personal.getId());

        //when
        recordRepository.save(personal);
        Long saveId = personal.getId();
        System.out.println(personal.getId());

        //then
        assertThat(personal).isEqualTo(getRepository.findOne(saveId));
    }
}
