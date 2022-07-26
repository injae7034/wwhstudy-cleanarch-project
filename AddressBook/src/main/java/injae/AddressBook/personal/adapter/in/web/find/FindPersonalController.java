package injae.AddressBook.personal.adapter.in.web.find;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.FindPersonalUseCase;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FindPersonalController {

    private final FindPersonalUseCase useCase;

    private final FindMemberQuery query;

    private List<Personal> personals;

    @GetMapping("/find")
    public String createForm(Model model) {
        model.addAttribute("findPersonalForm", new FindPersonalForm());

        if(personals == null) {
            model.addAttribute("personals", null);
        } else {
            model.addAttribute("personals", personals);
        }

        personals = null;

        return "personal/findPersonalForm";
    }

    @PostMapping("/find")
    public String findPersonal(
            @SessionAttribute(name = "loginMemberId") Long loginMemberId,
            @Valid FindPersonalForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "personal/findPersonalForm";
        }

        Member loginMember = query.findMember(loginMemberId);

        personals = useCase.findPersonalByName(loginMember, form.getName());

        return "redirect:/find";
    }

}
