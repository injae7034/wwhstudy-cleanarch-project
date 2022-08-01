package injae.AddressBook.member.adapter.in.api.find;

import injae.AddressBook.personal.domain.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindMemberResponse {

    private String email;
    private String name;
    private List<Personal> personals;

}
