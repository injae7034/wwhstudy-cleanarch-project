package injae.AddressBook.member.adapter.in.web.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginMemberForm {

    @NotEmpty(message = "아이디는 필수로 적어야 합니다.")
    @Email(message = "이메일 형식을 지켜주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수로 적어야 합니다.")
    private String password;

}
