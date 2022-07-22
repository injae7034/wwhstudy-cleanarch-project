package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.FindPersonalUseCase;
import injae.AddressBook.personal.application.port.out.FindPersonalRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;



class FindPersonalServiceTest {

    private final FindPersonalRepository repository =
            mock(FindPersonalRepository.class);

    private final FindPersonalUseCase useCase =
            new FindPersonalService(repository);

    private final String ANY_NAME = "홍길동";

    @Test
    void findPersonalTest() {

        //given & when
//        useCase.findPersonalByName(new FindPersonalCommand(ANY_NAME));

        //then
        verify(repository).findByName(ANY_NAME);

    }

}
