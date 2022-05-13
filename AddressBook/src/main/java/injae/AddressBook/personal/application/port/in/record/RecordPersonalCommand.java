package injae.AddressBook.personal.application.port.in.record;

import injae.AddressBook.common.PersonalCommandValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class RecordPersonalCommand
        extends PersonalCommandValidating<RecordPersonalCommand> {

    @NotNull @NotEmpty
    private final String name;
    @NotNull @NotEmpty
    private final String address;
    @NotNull @NotEmpty
    private final String telephoneNumber;
    @Email
    private final String emailAddress;

    public RecordPersonalCommand(String name, String address,
                                 String telephoneNumber, String emailAddress) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.validatePersonalCommand();
    }
}
