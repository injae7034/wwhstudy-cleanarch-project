package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.personal.domain.Personal;

public interface CorrectPersonalRepository {

    void update(Personal personal);

}
