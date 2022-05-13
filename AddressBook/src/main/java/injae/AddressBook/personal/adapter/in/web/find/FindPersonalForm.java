package injae.AddressBook.personal.adapter.in.web.find;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class FindPersonalForm {

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

}
