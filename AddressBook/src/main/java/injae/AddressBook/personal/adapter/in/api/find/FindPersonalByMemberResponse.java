package injae.AddressBook.personal.adapter.in.api.find;

import injae.AddressBook.personal.adapter.in.api.get.personal.GetPersonalByMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindPersonalByMemberResponse {

    private int count;
    private List<GetPersonalByMemberResponse> personals;
}
