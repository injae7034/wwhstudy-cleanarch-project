package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.personal.domain.Personal;

public interface RecordPersonalRepository {

    public void save(Personal personal);

}
