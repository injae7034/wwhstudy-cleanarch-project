package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.ErasePersonalRepository;
import injae.AddressBook.personal.application.port.out.GetPersonalsRepository;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaErasePersonalRepository.class, JpaRecordPersonalRepository.class,
        JpaGetPersonalsRepository.class})
class JpaErasePersonalRepositoryTest {

    @Autowired
    ErasePersonalRepository eraseRepository;

    @Autowired
    RecordPersonalRepository recordRepository;

    @Autowired
    GetPersonalsRepository getRepository;

    @Test
    void deleteTest() {
        //given
        Personal one = new Personal(
                "하길동",
                "서울시 중구",
                "024435960",
                "hong@naver.com"
        );
        recordRepository.save(one);

        Personal two = new Personal(
                "하길동",
                "서울시 서초구",
                "029948329",
                "hong@gmail.com"
        );
        recordRepository.save(two);

        Personal three = new Personal(
                "하길동",
                "서울시 강남구",
                "029942343",
                "hong@daum.com"
        );
        recordRepository.save(three);

        assertThat(getRepository.findAll()).hasSize(3);

        //when
        eraseRepository.delete(one);

        //then
        assertThat(getRepository.findAll()).hasSize(2);

        //when
        eraseRepository.delete(two);

        //then
        assertThat(getRepository.findAll()).hasSize(1);

        //when
        eraseRepository.delete(three);

        //then
        assertThat(getRepository.findAll()).hasSize(0);
    }
}
