package injae.AddressBook.personal.adapter.in.web;

import injae.AddressBook.personal.application.port.in.get.GetPersonalsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final GetPersonalsUseCase useCase;

    @RequestMapping("/")
    public String home(Model model) {
        log.info("home controller");
        model.addAttribute("personals", useCase.getPersonals());
        return "home";
    }

}
