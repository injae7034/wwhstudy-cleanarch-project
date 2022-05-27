package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.FindPersonalRepository;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaFindPersonalRepository.class, JpaRecordPersonalRepository.class})
@Transactional
class JpaFindPersonalRepositoryTest {

    @Autowired
    FindPersonalRepository findRepository;

    @Autowired
    RecordPersonalRepository recordRepository;

    @Test
    void findByNameTest() {

        //given
        Personal personal = new Personal(
                "하길동",
                "서울시 중구",
                "024435960",
                "hong@naver.com"
        );
        recordRepository.save(personal);

        personal = new Personal(
                "하길동",
                "서울시 서초구",
                "029948329",
                "hong@gmail.com"
        );
        recordRepository.save(personal);

        personal = new Personal(
                "하길동",
                "서울시 강남구",
                "029942343",
                "hong@daum.com"
        );
        recordRepository.save(personal);

        //when
        List<Personal> personals = findRepository.findByName(personal.getName());
        //then
        assertThat(personals).hasSize(3);

        for(Personal key : personals) {
            assertThat(key.getName()).isEqualTo("하길동");
        }

    }
}
