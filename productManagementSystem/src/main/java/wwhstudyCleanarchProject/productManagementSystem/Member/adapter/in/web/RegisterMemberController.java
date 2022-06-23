package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.in.RegisterMemberUseCase;
import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class RegisterMemberController {

    private final RegisterMemberUseCase useCase;

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("member") Member member) {
        return "members/registerMemberForm";
    }
}
