package injae.AddressBook.personal.adapter.in.web.erase;

import injae.AddressBook.personal.application.port.in.ErasePersonalUseCase;
import injae.AddressBook.personal.application.port.in.GetPersonalQuery;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ErasePersonalController {

    private final GetPersonalQuery query;
    private final ErasePersonalUseCase useCase;

    @GetMapping("/erase/{id}")
    public String erasePersonal(@PathVariable("id") Long id) {
        Personal personal = query.getPersonal(id);

        useCase.erasePersonal(personal);

        return "redirect:/";
    }

}
