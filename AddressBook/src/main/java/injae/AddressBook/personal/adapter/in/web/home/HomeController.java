package injae.AddressBook.personal.adapter.in.web.home;

import injae.AddressBook.personal.application.port.in.arrange.ArrangePersonalQuery;
import injae.AddressBook.personal.application.port.in.get.GetPersonalsQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final GetPersonalsQuery getQuery;

    private final ArrangePersonalQuery arrangeQuery;

    private final HomeForm homeForm = new HomeForm();

    @RequestMapping("/")
    public String home(Model model) {
        log.info("home controller");

        if(homeForm.isArrangeChecked() == false) {
            homeForm.setPersonals(getQuery.getPersonals());
        } else {
            homeForm.setPersonals(arrangeQuery.arrangePersonal());
        }

        model.addAttribute("personals", homeForm.getPersonals());
        model.addAttribute("isArrangeChecked",
                homeForm.isArrangeChecked());

        return "home";
    }

    @PostMapping("/")
    public String arrangePersonal(Model model) {
        if(homeForm.isArrangeChecked() == false) {
            homeForm.setArrangeChecked(true);
            homeForm.setPersonals(arrangeQuery.arrangePersonal());
        } else {
            homeForm.setArrangeChecked(false);
            homeForm.setPersonals(getQuery.getPersonals());
        }
        model.addAttribute("personals", homeForm.getPersonals());
        model.addAttribute("isArrangeChecked", homeForm.isArrangeChecked());

        return "home";
    }

}
