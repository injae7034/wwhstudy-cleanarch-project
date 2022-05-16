package injae.AddressBook.personal.application.port.in.get;

import injae.AddressBook.common.PersonalCommandValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class GetPersonalCommand
        extends PersonalCommandValidating<GetPersonalCommand> {

    @NotNull
    private final Long id;

    public GetPersonalCommand(Long id) {
        this.id = id;
        this.validatePersonalCommand();
    }
}
