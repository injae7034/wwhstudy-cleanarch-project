package injae.AddressBook.personal.adapter.in.api.get.personals;

import injae.AddressBook.personal.adapter.in.api.get.personal.GetPersonalByMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetPersonalsByMemberResponse {

    int count;
    List<GetPersonalByMemberResponse> personals;

}
