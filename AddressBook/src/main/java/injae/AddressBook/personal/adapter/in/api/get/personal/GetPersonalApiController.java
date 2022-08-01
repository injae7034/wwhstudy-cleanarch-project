package injae.AddressBook.personal.adapter.in.api.get.personal;

import injae.AddressBook.personal.application.port.in.GetPersonalQuery;
import injae.AddressBook.personal.domain.Personal;
import injae.AddressBook.personal.exception.PersonalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPersonalApiController {

    private final GetPersonalQuery getPersonalQuery;

    @GetMapping("/personals/{id}")
    public GetPersonalResponse getPersonal(@PathVariable Long id) {

        Personal findPersonal = getPersonalQuery.getPersonal(id);

        if (findPersonal == null) {
            throw new PersonalNotFoundException("해당하는 개인 정보를 찾을 수 없습니다.");
        }

        return new GetPersonalResponse(
                findPersonal.getName(),
                findPersonal.getAddress(),
                findPersonal.getTelephoneNumber(),
                findPersonal.getEmailAddress());
    }
}
