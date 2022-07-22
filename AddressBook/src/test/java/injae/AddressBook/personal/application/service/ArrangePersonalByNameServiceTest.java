package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.ArrangePersonalQuery;
import injae.AddressBook.personal.application.port.out.ArrangePersonalRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class ArrangePersonalByNameServiceTest {

    private final ArrangePersonalRepository repository =
            mock(ArrangePersonalRepository.class);

    private final ArrangePersonalQuery query =
            new ArrangePersonalByNameService(repository);

    @Test
    void arrangePersonal() {

        //given & when
        query.arrangePersonal();

        //then
        repository.arrange();

    }
}
