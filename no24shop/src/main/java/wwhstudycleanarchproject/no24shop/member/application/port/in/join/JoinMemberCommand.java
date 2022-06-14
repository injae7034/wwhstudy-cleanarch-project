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
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private Address address;

    private String type;
    private String role;

    public JoinMemberCommand(String email, String password,
                             String name, Address address,
                             String type, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        if(type == null) {
            this.type = "GENERAL";
        } else {
            this.type = type;
        }
        if (role == null) {
            this.role = "USER";
        } else {
            this.role = role;
        }
        this.validateCommand();
    }
}
