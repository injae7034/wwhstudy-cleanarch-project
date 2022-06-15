package wwhstudycleanarchproject.smallShop.Member.application.port.in.register;

import lombok.EqualsAndHashCode;
import lombok.Value;
import wwhstudycleanarchproject.smallShop.common.CommandValidating;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterMemberCommand
        extends CommandValidating<RegisterMemberCommand> {

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String address;

    public RegisterMemberCommand(String email, String password, String name, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;

        this.validateCommand();
    }
}
