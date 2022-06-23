package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.in.web.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class LoginMemberController {

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginMemberForm form) {
        return "members/loginMemberForm";
    }

}
