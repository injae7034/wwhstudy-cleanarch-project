package injae.AddressBook.common.exception;

public class PasswordNotSameAsConfirmPasswordException extends RuntimeException {
    public PasswordNotSameAsConfirmPasswordException() {
        super();
    }

    public PasswordNotSameAsConfirmPasswordException(String message) {
        super(message);
    }

    public PasswordNotSameAsConfirmPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotSameAsConfirmPasswordException(Throwable cause) {
        super(cause);
    }
}
