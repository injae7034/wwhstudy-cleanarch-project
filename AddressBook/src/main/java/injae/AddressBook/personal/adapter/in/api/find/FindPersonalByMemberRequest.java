package injae.AddressBook.personal.adapter.in.api.find;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindPersonalByMemberRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

}
