package injae.AddressBook.member.adapter.in.api.login;

import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.member.application.port.in.LoginMemberUseCase;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginMemberApiController {

    private final LoginMemberUseCase useCase;

    @GetMapping("/members/login")
    public ResponseEntity<LoginMemberResponse> loginMember(@RequestBody @Valid LoginMemberRequest request) {

        Member loginMember = useCase.loginMember(request.getEmail(), request.getPassword());

        if (loginMember == null) {
            throw new MemberNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        LoginMemberResponse loginMemberResponse = new LoginMemberResponse(loginMember.getId());

        return new ResponseEntity<>(loginMemberResponse, HttpStatus.OK);

    }
}
