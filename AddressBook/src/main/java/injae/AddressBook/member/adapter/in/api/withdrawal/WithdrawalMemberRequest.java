package injae.AddressBook.member.adapter.in.api.withdrawal;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WithdrawalMemberRequest {

    @NotBlank(message = "회원 탈퇴를 하려면 기존비밀번호를 입력하셔야 합니다.")
    private String password;

}
