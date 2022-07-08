package injae.AddressBook.personal.adapter.in.web.record;

import injae.AddressBook.personal.application.port.in.record.RecordPersonalCommand;
import injae.AddressBook.personal.application.port.in.record.RecordPersonalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RecordPersonalController {

    private final RecordPersonalUseCase useCase;

    @GetMapping("/record")
    public String createForm(Model model) {
        model.addAttribute("recordPersonalForm", new RecordPersonalForm());
        return "personal/recordPersonalForm";
    }

    @PostMapping("/record")
    public String recordPersonal(@Valid RecordPersonalForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "recordPersonalForm";
        }

        RecordPersonalCommand command = new RecordPersonalCommand(
                form.getName(),
                form.getAddress(),
                form.getTelephoneNumber(),
                form.getEmailAddress()
        );

        useCase.recordPersonal(command);

        return "redirect:/";
    }
}
