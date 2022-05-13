package injae.AddressBook.personal.application.port.in.get;

import injae.AddressBook.personal.domain.Personal;

import java.util.List;

public interface GetPersonalsUseCase {

    List<Personal> getPersonals();

}
