package injae.AddressBook.personal.application.service;

import injae.AddressBook.common.PersonalTestData;
import injae.AddressBook.personal.application.port.in.erase.ErasePersonalUseCase;
import injae.AddressBook.personal.application.port.out.ErasePersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;


class ErasePersonalServiceTest {

    private final ErasePersonalRepository repository =
            mock(ErasePersonalRepository.class);

    private final ErasePersonalUseCase useCase =
            new ErasePersonalService(repository);

    @Test
    void erasePersonalTest() {

        //given & when
        useCase.erasePersonal(PersonalTestData.defaultPersonal().build());

        //then
        verify(repository).delete(any(Personal.class));

    }

}
