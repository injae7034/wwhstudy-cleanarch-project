package injae.AddressBook.personal.adapter.in.webapplication.erase;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.ErasePersonalUseCase;
import injae.AddressBook.personal.application.port.in.GetPersonalQuery;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@RequestMapping("/personal")
public class ErasePersonalController {

    private final FindMemberQuery findMemberQuery;
    private final GetPersonalQuery getPersonalQuery;
    private final ErasePersonalUseCase erasePersonalUseCase;

    @GetMapping("/erase/{id}")
    public String erasePersonal(@SessionAttribute(name = "loginMemberId") Long loginMemberId,
            @PathVariable("id") Long id) {
        Member findMember = findMemberQuery.findMember(loginMemberId);

        Personal personal = getPersonalQuery.getPersonal(findMember, id);

        erasePersonalUseCase.erasePersonal(personal);

        return "redirect:/";
    }

}
