package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.GetPersonalsQuery;
import injae.AddressBook.personal.application.port.out.GetPersonalsRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPersonalsServiceTest {

    private final GetPersonalsRepository repository =
            mock(GetPersonalsRepository.class);

    private final GetPersonalsQuery query =
            new GetPersonalsService(repository);

    @Test
    void getPersonalsTest() {
        //given & when
        query.getPersonals();

        //then
        verify(repository).findAll();
    }
}
