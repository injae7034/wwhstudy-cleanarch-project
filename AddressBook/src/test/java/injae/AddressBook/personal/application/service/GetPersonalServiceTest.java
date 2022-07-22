package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.GetPersonalQuery;
import injae.AddressBook.personal.application.port.out.GetPersonalRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPersonalServiceTest {

    private final GetPersonalRepository repository =
            mock(GetPersonalRepository.class);

    private final GetPersonalQuery query =
            new GetPersonalService(repository);

    private final Long ANY_ID = 1L;

    @Test
    void getPersonal() {

        //given & when
//        query.getPersonal(new GetPersonalCommand(ANY_ID));

        //then
        verify(repository).findOne(ANY_ID);

    }
}
