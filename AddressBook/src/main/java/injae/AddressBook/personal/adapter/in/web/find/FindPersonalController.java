package injae.AddressBook.personal.adapter.in.web.find;

import injae.AddressBook.personal.adapter.in.web.record.RecordPersonalForm;
import injae.AddressBook.personal.application.port.in.find.FindPersonalCommand;
import injae.AddressBook.personal.application.port.in.find.FindPersonalUseCase;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FindPersonalController {

    private final FindPersonalUseCase useCase;

    @GetMapping("/find")
    public String createForm(Model model) {
        model.addAttribute("findPersonalForm", new FindPersonalForm());
        return "findPersonalForm";
    }

    @GetMapping("/find/{name}")
    public String findPersonal(@Valid FindPersonalForm form, BindingResult result,
                               Model model) {

        if (result.hasErrors()) {
            return "findPersonalForm";
        }

        List<Personal> personals = useCase.findPersonalByName(
                new FindPersonalCommand(form.getName())
        );

        model.addAttribute("personals", personals);

        return "redirect:/find";
    }

}
