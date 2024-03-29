package injae.AddressBook.personal.adapter.in.webapplication.record;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class RecordPersonalForm {

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;
    @NotEmpty(message = "주소는 필수입니다.")
    private String address;
    @NotEmpty(message = "전화번호는 필수입니다.")
    private String telephoneNumber;
    @Email(message = "이메일 형식을 지켜주세요.")
    private String emailAddress;

}
