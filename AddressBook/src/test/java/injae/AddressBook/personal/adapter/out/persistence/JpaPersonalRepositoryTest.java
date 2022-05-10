package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.PersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JpaPersonalRepositoryTest {

    @Autowired
    PersonalRepository repository;

    @Test
    public void save_test() {
        //given
        Personal personal = new Personal("홍길동", "서울시 중구",
                "01033459484", "hong@naver.com");
        System.out.println(personal.getId());

        //when
        repository.save(personal);
        Long saveId = personal.getId();

        System.out.println(personal.getId());

        //then
        Assertions.assertThat(personal).isEqualTo(repository.findOne(saveId));
    }
}
