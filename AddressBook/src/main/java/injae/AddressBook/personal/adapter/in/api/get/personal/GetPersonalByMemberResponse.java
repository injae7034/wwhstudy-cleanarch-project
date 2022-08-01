package injae.AddressBook.personal.adapter.in.api.get.personal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPersonalByMemberResponse {

    private String name;
    private String address;
    private String telephoneNumber;
    private String emailAddress;

}
