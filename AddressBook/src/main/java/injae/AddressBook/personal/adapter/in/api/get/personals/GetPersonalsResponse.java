package injae.AddressBook.personal.adapter.in.api.get.personals;

import injae.AddressBook.personal.adapter.in.api.get.personal.GetPersonalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetPersonalsResponse {

    int count;
    List<GetPersonalResponse> personals;

}
