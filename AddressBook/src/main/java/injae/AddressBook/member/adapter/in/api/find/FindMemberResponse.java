package injae.AddressBook.member.adapter.in.api.find;

import injae.AddressBook.personal.adapter.in.api.get.personal.GetPersonalByMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindMemberResponse {

    private String email;
    private String name;
    private int personalCount;
    private List<GetPersonalByMemberResponse> personals;

}
