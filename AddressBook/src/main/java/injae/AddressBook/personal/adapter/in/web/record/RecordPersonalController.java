package injae.AddressBook.personal.adapter.in.web.record;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.RecordPersonalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RecordPersonalController {

    private final RecordPersonalUseCase useCase;

    private final FindMemberQuery query;

    @GetMapping("/record")
    public String createForm(Model model) {
        model.addAttribute("recordPersonalForm", new RecordPersonalForm());
        return "personal/recordPersonalForm";
    }

    @PostMapping("/record")
    public String recordPersonal(@Valid RecordPersonalForm form, BindingResult result,
                                 @SessionAttribute(name = "loginMemberId")
                                         Long loginMemberId) {

        if (result.hasErrors()) {
            return "personal/recordPersonalForm";
        }

        Member loginMember = query.findMember(loginMemberId);

        useCase.recordPersonal(form.getName(), form.getAddress(),
                form.getTelephoneNumber(), form.getEmailAddress(), loginMember);

        return "redirect:/";
    }
}
