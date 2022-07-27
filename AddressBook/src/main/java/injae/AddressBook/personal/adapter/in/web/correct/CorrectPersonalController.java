package injae.AddressBook.personal.adapter.in.web.correct;

import injae.AddressBook.personal.application.port.in.CorrectPersonalUseCase;
import injae.AddressBook.personal.application.port.in.GetPersonalQuery;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/personal")
public class CorrectPersonalController {

    private final GetPersonalQuery query;
    private final CorrectPersonalUseCase useCase;

    @GetMapping("/correct/{id}")
    public String createForm(@PathVariable("id") Long id, Model model) {
        Personal personal = query.getPersonal(id);

        model.addAttribute("correctPersonalForm",
                new CorrectPersonalForm(
                        personal.getId(),
                        personal.getName(),
                        personal.getAddress(),
                        personal.getTelephoneNumber(),
                        personal.getEmailAddress()));

        return "personal/correctPersonalForm";
    }

    @PostMapping("/correct/{id}")
    public String correctPersonal(@Valid CorrectPersonalForm correctPersonalForm,
                                  BindingResult result) {

        if (result.hasErrors()) {
            return "correctPersonalForm";
        }

        useCase.correctPersonal(correctPersonalForm.getId(),
                correctPersonalForm.getAddress(),
                correctPersonalForm.getTelephoneNumber(),
                correctPersonalForm.getEmailAddress());

        return "redirect:/";
    }
}
