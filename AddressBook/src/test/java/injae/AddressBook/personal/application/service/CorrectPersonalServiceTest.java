package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.correct.CorrectPersonalCommand;
import injae.AddressBook.personal.application.port.in.correct.CorrectPersonalUseCase;
import injae.AddressBook.personal.application.port.out.CorrectPersonalRepository;
import injae.AddressBook.personal.domain.Personal;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;


class CorrectPersonalServiceTest {

    private final CorrectPersonalRepository repository =
            mock(CorrectPersonalRepository.class);

    private final CorrectPersonalUseCase useCase =
            new CorrectPersonalService(repository);

    @Test
    void correctPersonalTest() {

        //given & when
        useCase.correctPersonal(new CorrectPersonalCommand(
                1L,
                "홍길동",
                "서울시 중구",
                "023349090",
                "hong@naver.com"
        ));

        //then
        verify(repository).update(any(Personal.class));

    }
}
