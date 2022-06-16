package wwhstudycleanarchproject.smallShop.Member.adapter.in.web.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
public class RegisterMemberData {

    @NotEmpty(message = "이메일을 id로 사용하기 때문에 이메일 입력은 필수입니다.")
    @Email(message = "이메일 형식을 지켜서 입력해주세요.")
    private String email;
    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이로 입력해주세요.")
    private String password;
    @NotEmpty(message = "이름 입력을 필수입니다.")
    private String name;
    @NotEmpty(message = "주소 입력은 필수입니다.")
    private String address;

}
