package injae.AddressBook.member.adapter.in.api.change;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeMemberRequest {

    @NotBlank(message = "비밀번호 변경하기 전에 기존 비밀번호를 먼저 적어야 합니다.")
    private String originalPassword;

    @NotBlank(message = "바꿀 비밀번호는 필수로 적어야 합니다.")
    private String changePassword;

    @NotBlank(message = "확인 비밀번호는 필수로 적어야 합니다.")
    private String confirmPassword;

}
