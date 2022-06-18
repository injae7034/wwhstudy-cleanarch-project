package wwhstudycleanarchproject.smallShop.Member.adapter.in.web.register;

// ToDo
// 가입 -> POST /users => 가입 정보(DTO) -> email이 unique key!

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.RegisterMemberUseCase;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class RegisterMemberController {

    private final RegisterMemberUseCase useCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void signUpMember(@RequestBody @Valid RegisterMemberData registerMemberData) {

        Member member = new Member(
                registerMemberData.getEmail(),
                registerMemberData.getPassword(),
                registerMemberData.getName(),
                registerMemberData.getAddress()
        );

        useCase.registerMember(member);
    }
}
