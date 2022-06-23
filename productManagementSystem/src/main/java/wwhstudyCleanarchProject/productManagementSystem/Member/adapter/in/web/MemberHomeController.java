package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

@Controller
@RequiredArgsConstructor
public class MemberHomeController {
    @GetMapping("/")
    public String memberHome(
            @SessionAttribute(name = "loginMember", required = false) Member loginMember,
            Model model) {

        //세션에 회원 데이타가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
