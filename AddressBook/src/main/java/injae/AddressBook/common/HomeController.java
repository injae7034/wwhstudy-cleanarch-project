package injae.AddressBook.common;

import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = "loginMember", required = false) Member loginMember,
            @SessionAttribute(name = "isRegisteringMemberSucceed", required = false)
                    Boolean isRegisteringMemberSucceed,
            HttpServletRequest request,
            Model model) {

        //세션에 회원 데이타가 없으면 home
        if (loginMember == null) {
            model.addAttribute("isRegisteringMemberSucceed", isRegisteringMemberSucceed);

            //회원가입 성공 여부 초기화시키기
            isRegisteringMemberSucceed = null;
            //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성함
            HttpSession session = request.getSession();
            //세션에 초기화된 회원가입 성공 여부 정보 보관
            session.setAttribute("isRegisteringMemberSucceed",
                    isRegisteringMemberSucceed);

            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }


}
