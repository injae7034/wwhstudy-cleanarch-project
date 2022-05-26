package injae.AddressBook.personal.application.service;

import injae.AddressBook.common.PersonalTestData;
import injae.AddressBook.personal.application.port.in.find.FindPersonalCommand;
import injae.AddressBook.personal.application.port.in.find.FindPersonalUseCase;
import injae.AddressBook.personal.application.port.in.get.GetPersonalQuery;
import injae.AddressBook.personal.application.port.in.record.RecordPersonalCommand;
import injae.AddressBook.personal.application.port.in.record.RecordPersonalUseCase;
import injae.AddressBook.personal.domain.Personal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FindPersonalServiceTest {

    @Autowired
    FindPersonalUseCase findUseCase;
    @Autowired
    RecordPersonalUseCase recordUseCase;

    @Test
    void findPersonalTest() {
        //given
        Personal one = new Personal(
                "하길동",
                "서울시 중구",
                "024435960",
                "hong@naver.com"
        );

        Personal two = new Personal(
                "하길동",
                "서울시 서초구",
                "029948329",
                "hong@gmail.com"
        );

        Personal three = new Personal(
                "하길동",
                "서울시 강남구",
                "029942343",
                "hong@daum.com"
        );

        RecordPersonalCommand recordCommand = new RecordPersonalCommand(
                one.getName(),
                one.getAddress(),
                one.getTelephoneNumber(),
                one.getEmailAddress()
        );

        recordUseCase.recordPersonal(recordCommand);

        recordCommand = new RecordPersonalCommand(
                two.getName(),
                two.getAddress(),
                two.getTelephoneNumber(),
                two.getEmailAddress()
        );

        recordUseCase.recordPersonal(recordCommand);

        recordCommand = new RecordPersonalCommand(
                three.getName(),
                three.getAddress(),
                three.getTelephoneNumber(),
                three.getEmailAddress()
        );

        recordUseCase.recordPersonal(recordCommand);

        //when
        List<Personal> personals = findUseCase.findPersonalByName(
                new FindPersonalCommand("하길동"));

        //then
        assertThat(personals).hasSize(3);

        for(Personal personal : personals) {
            assertThat(personal.getName()).isEqualTo("하길동");
        }

    }

}
