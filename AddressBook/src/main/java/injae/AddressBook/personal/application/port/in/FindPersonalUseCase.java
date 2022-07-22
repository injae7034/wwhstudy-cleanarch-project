package injae.AddressBook.personal.application.port.in;

import injae.AddressBook.personal.domain.Personal;

import java.util.List;

public interface FindPersonalUseCase {

    List<Personal> findPersonalByName(String name);

}
