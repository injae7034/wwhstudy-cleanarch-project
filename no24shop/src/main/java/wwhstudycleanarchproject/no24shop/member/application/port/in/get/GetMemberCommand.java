package wwhstudycleanarchproject.no24shop.member.application.port.in.get;

import lombok.EqualsAndHashCode;
import lombok.Value;
import wwhstudycleanarchproject.no24shop.common.CommandValidating;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class GetMemberCommand
        extends CommandValidating<GetMemberCommand> {

    @NotNull
    private final Long id;

    public GetMemberCommand(Long id) {
        this.id = id;
        this.validateCommand();
    }
}
