package injae.AddressBook.personal.adapter.in.web.correct;

import injae.AddressBook.personal.application.port.in.correct.CorrectPersonalCommand;
import injae.AddressBook.personal.application.port.in.correct.CorrectPersonalUseCase;
import injae.AddressBook.personal.application.port.in.get.GetPersonalCommand;
import injae.AddressBook.personal.application.port.in.get.GetPersonalQuery;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CorrectPersonalController {

    private final GetPersonalQuery query;
    private final CorrectPersonalUseCase useCase;

    @GetMapping("/correct/{id}")
    public String createForm(@PathVariable("id") Long id, Model model) {
        Personal personal = query.getPersonal(new GetPersonalCommand(id));

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

        CorrectPersonalCommand command = new CorrectPersonalCommand(
                correctPersonalForm.getId(),
                correctPersonalForm.getName(),
                correctPersonalForm.getAddress(),
                correctPersonalForm.getTelephoneNumber(),
                correctPersonalForm.getEmailAddress());

        useCase.correctPersonal(command);

        return "redirect:/";
    }
}
