package injae.AddressBook.personal.application.port.in.get;

import injae.AddressBook.personal.domain.Personal;

public interface GetPersonalQuery {

    Personal getPersonal(GetPersonalCommand command);

}
