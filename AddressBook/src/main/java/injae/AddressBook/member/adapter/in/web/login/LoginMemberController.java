package injae.AddressBook.member.adapter.in.web.login;

import injae.AddressBook.member.application.port.in.LoginMemberUseCase;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class LoginMemberController {

    private final LoginMemberUseCase useCase;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginMemberForm form) {
        return "member/loginMemberForm";
    }

    @PostMapping("/login")
    public String loginMember(@Valid @ModelAttribute LoginMemberForm form,
                              BindingResult bindingResult,
                              @RequestParam(defaultValue = "/") String redirectURL,
                              HttpServletRequest request) {

        //공백이 있을 경우 예외 발생
        if (bindingResult.hasErrors()) {
            return "members/login";
        }

        Member loginMember = useCase.loginMember(form.getEmail(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail",
                    "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginMemberForm";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성함
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute("loginMember", loginMember);

        return "redirect:" + redirectURL;
    }
}
