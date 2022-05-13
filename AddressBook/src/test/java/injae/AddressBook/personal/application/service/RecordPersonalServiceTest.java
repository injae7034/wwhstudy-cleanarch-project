package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.record.RecordPersonalCommand;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class RecordPersonalServiceTest {

    @Autowired
    RecordPersonalService service;
    @Autowired
    RecordPersonalRepository recordPersonalRepository;

    @Test
    void recordPersonalTest() {
        //given
        RecordPersonalCommand command = new RecordPersonalCommand(
                "홍길동",
                "서울시 중구",
                "01033450093",
                "hong@naver.com"
        );

        //when
        Long saveId = service.recordPersonal(command);

//        //then
//        assertThat(command.getName())
//                .isEqualTo(recordPersonalRepository.findOne(saveId).getName());
//        assertThat(command.getAddress())
//                .isEqualTo(recordPersonalRepository.findOne(saveId).getAddress());
//        assertThat(command.getTelephoneNumber())
//                .isEqualTo(recordPersonalRepository.findOne(saveId).getTelephoneNumber());
//        assertThat(command.getEmailAddress())
//                .isEqualTo(recordPersonalRepository.findOne(saveId).getEmailAddress());

    }
}
