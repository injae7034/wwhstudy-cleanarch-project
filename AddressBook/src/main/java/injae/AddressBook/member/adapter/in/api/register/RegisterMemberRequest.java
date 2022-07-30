package injae.AddressBook.member.adapter.in.api.register;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterMemberRequest {

    @NotBlank(message = "아이디는 필수로 적어야 합니다.")
    @Email(message = "이메일 형식을 지켜주세요.")
    private String email; // 로그인 ID

    @NotBlank(message = "비밀번호는 필수로 적어야 합니다.")
    private String password;

    @NotBlank(message = "확인 비밀번호는 비밀번호와 똑같이 적어야 합니다.")
    private String confirmPassword;

    @NotBlank(message = "이름은 필수로 적어야 합니다.")
    private String name; // 사용자 이름

}
