package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.in.web.register;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class RegisterMemberForm {

    @NotEmpty(message = "아이디는 필수로 적어야 합니다.")
    @Email(message = "이메일 형식을 지켜주세요.")
    private String email; // 로그인 ID

    @NotEmpty(message = "비밀번호는 필수로 적어야 합니다.")
    @Range(min = 6, max = 12,
            message = "비밀번호는 최소 6글자에서 최대 12글자 사이로 입력하셔야 합니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인도 필수로 적어야 합니다.")
    @Range(min = 6, max = 12,
            message = "비밀번호는 최소 6글자에서 최대 12글자 사이로 입력하셔야 합니다.")
    private String confirmPassword;

    @NotEmpty(message = "이름은 필수로 적어야 합니다.")
    private String name; // 사용자 이름

}
