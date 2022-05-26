package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.erase.ErasePersonalUseCase;
import injae.AddressBook.personal.application.port.in.get.GetPersonalCommand;
import injae.AddressBook.personal.application.port.in.get.GetPersonalQuery;
import injae.AddressBook.personal.application.port.in.get.GetPersonalsQuery;
import injae.AddressBook.personal.application.port.in.record.RecordPersonalCommand;
import injae.AddressBook.personal.application.port.in.record.RecordPersonalUseCase;
import injae.AddressBook.personal.domain.Personal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ErasePersonalServiceTest {

    @Autowired
    ErasePersonalUseCase eraseUseCase;
    @Autowired
    GetPersonalQuery personalQuery;
    @Autowired
    RecordPersonalUseCase recordUseCase;
    @Autowired
    GetPersonalsQuery personalsQuery;

    @Test
    void erasePersonalTest() {
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

        Long ondId = recordUseCase.recordPersonal(recordCommand);

        recordCommand = new RecordPersonalCommand(
                two.getName(),
                two.getAddress(),
                two.getTelephoneNumber(),
                two.getEmailAddress()
        );

        Long twoId = recordUseCase.recordPersonal(recordCommand);

        recordCommand = new RecordPersonalCommand(
                three.getName(),
                three.getAddress(),
                three.getTelephoneNumber(),
                three.getEmailAddress()
        );

        Long threeId = recordUseCase.recordPersonal(recordCommand);

        //when
        eraseUseCase.erasePersonal(personalQuery.getPersonal(
                new GetPersonalCommand(ondId)));

        //then
        assertThat(personalsQuery.getPersonals()).hasSize(2);

        //when
        eraseUseCase.erasePersonal(personalQuery.getPersonal(
                new GetPersonalCommand(twoId)));

        //then
        assertThat(personalsQuery.getPersonals()).hasSize(1);

        //when
        eraseUseCase.erasePersonal(personalQuery.getPersonal(
                new GetPersonalCommand(threeId)));

        //then
        assertThat(personalsQuery.getPersonals()).hasSize(0);
    }

}
