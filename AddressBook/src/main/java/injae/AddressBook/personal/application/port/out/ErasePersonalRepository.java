package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.personal.domain.Personal;

public interface ErasePersonalRepository {

    void delete(Personal personal);

}
