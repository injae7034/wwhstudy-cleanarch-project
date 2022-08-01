package injae.AddressBook.personal.adapter.in.api.correct;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CorrectPersonalByMemberRequest {

    @NotBlank(message = "주소는 필수입니다.")
    private String address;
    @NotBlank(message = "전화번호는 필수입니다.")
    private String telephoneNumber;
    @Email(message = "이메일 형식을 지켜주세요.")
    private String emailAddress;
}
