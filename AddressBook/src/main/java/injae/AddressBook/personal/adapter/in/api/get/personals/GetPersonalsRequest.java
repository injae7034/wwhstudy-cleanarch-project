package injae.AddressBook.personal.adapter.in.api.get.personals;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetPersonalsRequest {

    @NotBlank(message = "회원 id가 필요합니다.")
    private Long memberId;

}
