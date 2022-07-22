package injae.AddressBook.personal.application.port.in;

import injae.AddressBook.personal.domain.Personal;

public interface GetPersonalQuery {

    Personal getPersonal(Long id);

}
