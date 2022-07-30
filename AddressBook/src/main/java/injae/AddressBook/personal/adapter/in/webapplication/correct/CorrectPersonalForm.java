package injae.AddressBook.personal.adapter.in.webapplication.correct;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CorrectPersonalForm {

    private Long id;
    private String name;

    @NotEmpty(message = "주소는 필수입니다.")
    private String address;
    @NotEmpty(message = "전화번호는 필수입니다.")
    private String telephoneNumber;
    @Email(message = "이메일 형식을 지켜주세요.")
    private String emailAddress;

    public CorrectPersonalForm(Long id, String name, String address,
                               String telephoneNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }

}
