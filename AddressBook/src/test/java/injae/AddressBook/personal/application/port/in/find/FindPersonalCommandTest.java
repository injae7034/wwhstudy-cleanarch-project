package injae.AddressBook.personal.application.port.in.find;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FindPersonalCommandTest {
    @Test
    public void findPersonalCommand_validate_test() {

        assertThatThrownBy(() -> {
            new FindPersonalCommand(null);
        }).isInstanceOf(ConstraintViolationException.class);

    }
}
