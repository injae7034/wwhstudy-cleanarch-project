package injae.AddressBook.common.exception;

public class NotSamePasswordException extends RuntimeException {
    public NotSamePasswordException() {
        super();
    }

    public NotSamePasswordException(String message) {
        super(message);
    }

    public NotSamePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSamePasswordException(Throwable cause) {
        super(cause);
    }
}
