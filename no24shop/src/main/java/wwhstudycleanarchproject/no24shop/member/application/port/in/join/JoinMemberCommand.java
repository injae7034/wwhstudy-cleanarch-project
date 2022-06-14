package wwhstudycleanarchproject.no24shop.member.application.port.in.join;

import lombok.EqualsAndHashCode;
import lombok.Value;
import wwhstudycleanarchproject.no24shop.common.CommandValidating;
import wwhstudycleanarchproject.no24shop.domain.Address;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class JoinMemberCommand
        extends CommandValidating<JoinMemberCommand> {

    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private String password;
    @NotNull
    private String role;

    private Address address;

    private String email;

    public JoinMemberCommand(String name, String type,
                             String password, String role,
                             Address address, String email) {
        this.name = name;
        this.type = type;
        this.password = password;
        this.role = role;
        this.address = address;
        this.email = email;
        this.validateCommand();
    }
}
