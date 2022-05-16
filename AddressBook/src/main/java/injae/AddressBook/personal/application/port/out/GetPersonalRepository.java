package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.personal.domain.Personal;

public interface GetPersonalRepository {

    Personal findOne(Long id);

}
