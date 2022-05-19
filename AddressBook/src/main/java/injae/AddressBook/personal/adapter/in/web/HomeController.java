package injae.AddressBook.personal.adapter.in.web;

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

    @RequestMapping("/")
    public String home(Model model) {
        log.info("home controller");
        model.addAttribute("personals", getQuery.getPersonals());
        model.addAttribute("isArrangeChecked", false);

        return "home";
    }

    @PostMapping("/")
    public String arrangePersonal(Model model) {
        model.addAttribute("personals", arrangeQuery.arrangePersonal());
        model.addAttribute("isArrangeChecked", true);
        return "home";
    }

}
