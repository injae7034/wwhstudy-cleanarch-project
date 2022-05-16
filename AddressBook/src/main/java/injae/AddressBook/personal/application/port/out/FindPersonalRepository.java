package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.personal.domain.Personal;

import java.util.List;

public interface FindPersonalRepository {

    List<Personal> findByName(String name);

}
