package injae.AddressBook.member.adapter.in.api.login;

import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.member.application.port.in.LoginMemberUseCase;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginMemberApiController {

    private final LoginMemberUseCase useCase;

    @PostMapping("/members/login")
    public void loginMember(
            @RequestBody @Valid LoginMemberRequest request,
            HttpServletRequest httpServletRequest) {

        Member loginMember = useCase.loginMember(request.getEmail(), request.getPassword());

        if (loginMember == null) {
            throw new MemberNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성함
        HttpSession session = httpServletRequest.getSession();
        //세션에 로그인 회원 id 정보 보관
        session.setAttribute("loginMemberId", loginMember.getId());
    }
}
