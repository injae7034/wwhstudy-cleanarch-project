package injae.AddressBook.personal.application.port.in.correct;


import injae.AddressBook.common.PersonalCommandValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class CorrectPersonalCommand
        extends PersonalCommandValidating<CorrectPersonalCommand> {

    @NotNull
    private final Long id;
    @NotNull
    private final String name;
    @NotNull
    private final String address;
    @NotNull
    private final String telephoneNumber;

    private final String emailAddress;

    public CorrectPersonalCommand(Long id, String name, String address,
                                  String telephoneNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.validatePersonalCommand();
    }
}
