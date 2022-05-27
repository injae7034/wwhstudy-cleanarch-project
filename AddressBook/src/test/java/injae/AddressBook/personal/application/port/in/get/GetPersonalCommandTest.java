package injae.AddressBook.personal.application.port.in.get;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GetPersonalCommandTest {

    @Test
    public void getPersonalCommand_validate_test() {

        assertThatThrownBy(() -> {
            new GetPersonalCommand(null);
        }).isInstanceOf(ConstraintViolationException.class);
    }

}
