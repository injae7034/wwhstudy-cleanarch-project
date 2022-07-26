package injae.AddressBook.common;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.ArrangePersonalQuery;
import injae.AddressBook.personal.application.port.in.GetPersonalsQuery;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final GetPersonalsQuery getPersonalsQuery;

    private final ArrangePersonalQuery arrangePersonalQuery;

    private final FindMemberQuery findMemberQuery;

    private boolean isArrangeChecked = false;

    private List<Personal> personals;

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = "loginMemberId", required = false) Long loginMemberId,
            HttpServletRequest request,
            Model model) {

        //세션에 회원 데이타가 없으면 home
        if (loginMemberId == null) {
            //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성함
            HttpSession session = request.getSession();

            return "home";
        }

        Member loginMember = findMemberQuery.findMember(loginMemberId);

        if(isArrangeChecked == false) {
            personals = getPersonalsQuery.getPersonals(loginMember);
        } else {
            personals = arrangePersonalQuery.arrangePersonal(loginMember);
        }

        model.addAttribute("personals", personals);
        model.addAttribute("isArrangeChecked", isArrangeChecked);
        model.addAttribute("member", loginMember);

        return "loginHome";
    }

    @PostMapping("/")
    public String arrangePersonal( @SessionAttribute(name = "loginMemberId", required = false)
                                               Long loginMemberId,
                                   HttpServletRequest request,
                                   Model model) {

        Member loginMember = findMemberQuery.findMember(loginMemberId);

        if(isArrangeChecked == false) {
            isArrangeChecked = true;
            personals = arrangePersonalQuery.arrangePersonal(loginMember);
        } else {
            isArrangeChecked = false;
            personals = getPersonalsQuery.getPersonals(loginMember);
        }
        model.addAttribute("personals", personals);
        model.addAttribute("isArrangeChecked", isArrangeChecked);
        model.addAttribute("member", loginMember);

        return "loginHome";
    }

}
