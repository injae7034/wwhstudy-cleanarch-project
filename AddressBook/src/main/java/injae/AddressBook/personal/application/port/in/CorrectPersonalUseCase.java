package injae.AddressBook.personal.application.port.in;

public interface CorrectPersonalUseCase {

    Long correctPersonal(Long id, String address,
                         String telephoneNumber, String emailAddress);

}
