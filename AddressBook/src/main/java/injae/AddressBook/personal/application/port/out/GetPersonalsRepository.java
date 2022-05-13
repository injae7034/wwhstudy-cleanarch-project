package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.personal.domain.Personal;

import java.util.List;

public interface GetPersonalsRepository {

    public List<Personal> findAll();

}
