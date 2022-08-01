package injae.AddressBook.personal.exception;

public class PersonalNotFoundException extends RuntimeException {
    public PersonalNotFoundException() {
        super();
    }

    public PersonalNotFoundException(String message) {
        super(message);
    }

    public PersonalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonalNotFoundException(Throwable cause) {
        super(cause);
    }
}
