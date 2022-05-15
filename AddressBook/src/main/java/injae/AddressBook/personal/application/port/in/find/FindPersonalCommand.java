package injae.AddressBook.personal.application.port.in.find;

import injae.AddressBook.common.PersonalCommandValidating;
import injae.AddressBook.personal.application.port.in.record.RecordPersonalCommand;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class FindPersonalCommand
        extends PersonalCommandValidating<RecordPersonalCommand> {

    @NotNull
    private final String name;

    public FindPersonalCommand(String name) {
        this.name = name;
        this.validatePersonalCommand();
    }
}

