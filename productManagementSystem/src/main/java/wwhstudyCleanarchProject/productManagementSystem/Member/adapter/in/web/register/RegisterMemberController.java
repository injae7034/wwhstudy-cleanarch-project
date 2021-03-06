package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.in.web.register;

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
    public String registerForm(@ModelAttribute RegisterMemberForm form) {
        return "members/registerMemberForm";
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute RegisterMemberForm form,
                                 BindingResult bindingResult) {

        //비밀번호와 확인 비밀번호가 서로 일치 하지 않으면 예외 발생
       if(!form.getPassword().equals(form.getConfirmPassword())) {
            bindingResult.reject("notSamePassword",
                    "비밀번호와 확인 비밀번호가 서로 일치하지 않습니다.");
        }

        //예외가 생기면 다시 돌아감
        if (bindingResult.hasErrors()) {
            return "members/registerMemberForm";
        }

        //성공 로직
        Member newMember = new Member(form.getEmail(), form.getPassword(), form.getName());

        try {
            useCase.registerMember(newMember);
        } catch (IllegalStateException e) {
            //이미 존재하는 아이디를 입력하여 회원 가입할 경우
            bindingResult.reject("duplicatedMember",
                    e.getMessage());
            //예외가 생겨서 다시 돌아감
            return "members/registerMemberForm";
        }

        //예와가 없으면 홈화면으로 돌아감
        return "redirect:/";
    }
}
