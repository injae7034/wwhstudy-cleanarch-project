package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.personal.domain.Personal;

import java.util.List;

public interface PersonalRepository {

    public void save(Personal personal);

    public Personal findOne(Long id);

    public List<Personal> findAll();

    public List<Personal> findByName(String name);

}
