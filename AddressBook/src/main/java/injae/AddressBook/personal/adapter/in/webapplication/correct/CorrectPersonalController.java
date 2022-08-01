package injae.AddressBook.personal.adapter.in.webapplication.correct;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
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
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/personal")
public class CorrectPersonalController {

    private final FindMemberQuery findMemberQuery;
    private final GetPersonalQuery getPersonalQuery;
    private final CorrectPersonalUseCase correctPersonalUseCase;

    @GetMapping("/correct/{id}")
    public String createForm(@SessionAttribute(name = "loginMemberId") Long loginMemberId,
            @PathVariable("id") Long id, Model model) {
        Member findMember = findMemberQuery.findMember(loginMemberId);

        Personal personal = getPersonalQuery.getPersonal(findMember, id);

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

        correctPersonalUseCase.correctPersonal(correctPersonalForm.getId(),
                correctPersonalForm.getAddress(),
                correctPersonalForm.getTelephoneNumber(),
                correctPersonalForm.getEmailAddress());

        return "redirect:/";
    }
}
