package injae.AddressBook.member.adapter.in.webapplication.change;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChangePasswordForm {

    @NotEmpty(message = "기존 비밀번호는 필수로 적어야 합니다.")
    private String originalPassword;

    @NotEmpty(message = "바꿀 비밀번호는 필수로 적어야 합니다.")
    private String changePassword;

    @NotEmpty(message = "확인 비밀번호는 필수로 적어야 합니다.")
    private String confirmPassword;

}
