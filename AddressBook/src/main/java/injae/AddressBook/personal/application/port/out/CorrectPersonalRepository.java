package injae.AddressBook.personal.application.port.out;

public interface CorrectPersonalRepository {

    void update(Long id, String address,
                String telephoneNumber, String emailAddress);

}
