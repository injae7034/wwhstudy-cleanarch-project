package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.in.RegisterMemberUseCase;
import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class RegisterMemberController {

    private final RegisterMemberUseCase useCase;

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("member") Member member) {
        return "members/registerMemberForm";
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/registerMemberForm";
        }

        useCase.registerMember(member);

        return "redirect:/";
    }
}
