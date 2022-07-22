package injae.AddressBook.personal.adapter.in.web.home;

import injae.AddressBook.personal.application.port.in.ArrangePersonalQuery;
import injae.AddressBook.personal.application.port.in.GetPersonalsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/personal")
public class PersonalHomeController {

    private final GetPersonalsQuery getQuery;

    private final ArrangePersonalQuery arrangeQuery;

    private final PersonalHomeForm personalHomeForm = new PersonalHomeForm();

    @GetMapping
    public String home(Model model) {

        if(personalHomeForm.isArrangeChecked() == false) {
            personalHomeForm.setPersonals(getQuery.getPersonals());
        } else {
            personalHomeForm.setPersonals(arrangeQuery.arrangePersonal());
        }

        model.addAttribute("personals", personalHomeForm.getPersonals());
        model.addAttribute("isArrangeChecked",
                personalHomeForm.isArrangeChecked());

        return "loginHome";
    }

    @PostMapping
    public String arrangePersonal(Model model) {
        if(personalHomeForm.isArrangeChecked() == false) {
            personalHomeForm.setArrangeChecked(true);
            personalHomeForm.setPersonals(arrangeQuery.arrangePersonal());
        } else {
            personalHomeForm.setArrangeChecked(false);
            personalHomeForm.setPersonals(getQuery.getPersonals());
        }
        model.addAttribute("personals", personalHomeForm.getPersonals());
        model.addAttribute("isArrangeChecked", personalHomeForm.isArrangeChecked());

        return "loginHome";
    }

}
