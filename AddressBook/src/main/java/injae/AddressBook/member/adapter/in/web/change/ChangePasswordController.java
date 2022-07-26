package injae.AddressBook.member.adapter.in.web.change;

import injae.AddressBook.member.application.port.in.ChangePasswordUseCase;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class ChangePasswordController {

    private final ChangePasswordUseCase useCase;

    @GetMapping("/change")
    public String changePasswordForm(@ModelAttribute ChangePasswordForm form) {
        return "member/changePasswordForm";
    }

    @PostMapping("/change")
    public String changePassword(@Valid @ModelAttribute ChangePasswordForm form,
                                 BindingResult bindingResult,
                                 @RequestParam(defaultValue = "/") String redirectURL,
                                 @SessionAttribute(name = "loginMember") Member loginMember) {
        //예외처리
        if (bindingResult.hasErrors()) {
            return "member/changePasswordForm";
        }

        if (loginMember.getPassword().equals(form.getOriginalPassword()) == false) {
            bindingResult.reject("NotSameOriginalPassword",
                    "기존 비밀번호가 일치하지 않습니다.");
            return "member/changePasswordForm";
        }

        if (form.getChangePassword().equals(form.getConfirmPassword()) == false) {
            bindingResult.reject("NotSameChangePassword",
                    "변경 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            return "member/changePasswordForm";
        }

        //비밀번호 변경
        useCase.changePassword(loginMember.getId(), form.getChangePassword());

        return "redirect:" + redirectURL;
    }
}
