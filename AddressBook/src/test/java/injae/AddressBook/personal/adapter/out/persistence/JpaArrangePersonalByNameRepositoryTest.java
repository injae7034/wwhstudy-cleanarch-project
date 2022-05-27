package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.ArrangePersonalRepository;
import injae.AddressBook.personal.application.port.out.GetPersonalsRepository;
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
@Import({JpaArrangePersonalByNameRepository.class,
        JpaGetPersonalsRepository.class, JpaRecordPersonalRepository.class})
@Transactional
class JpaArrangePersonalByNameRepositoryTest {

    @Autowired
    ArrangePersonalRepository arrangeRepository;

    @Autowired
    GetPersonalsRepository getRepository;

    @Autowired
    RecordPersonalRepository recordRepository;

    @Test
    void arrange() {

        //given
        Personal personal = new Personal(
                "홍길동",
                "서울시 중구",
                "028839920",
                "hong@naver.com"
        );
        recordRepository.save(personal);

        personal = new Personal(
                "박길동",
                "서울시 서초구",
                "023394593",
                "park@naver.com"
        );
        recordRepository.save(personal);

        personal = new Personal(
                "김길동",
                "서울시 강남구",
                "025439090",
                "kim@naver.com"
        );
        recordRepository.save(personal);

        //when
        List<Personal> originalPersonals = getRepository.findAll();
        List<Personal> arrangedPersonals = arrangeRepository.arrange();

        //then
        assertThat(originalPersonals).isNotEqualTo(arrangedPersonals);

        System.out.println("정렬 전");
        for (Personal value : originalPersonals) {
            System.out.println(value.getName());
        }
        System.out.println();
        System.out.println("정렬 후");
        for (Personal value : arrangedPersonals) {
            System.out.println(value.getName());
        }
    }
}
