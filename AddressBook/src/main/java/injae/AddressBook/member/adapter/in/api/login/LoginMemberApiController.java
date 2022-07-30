package injae.AddressBook.member.adapter.in.api.login;

import injae.AddressBook.member.application.port.in.LoginMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginMemberApiController {

    private final LoginMemberUseCase useCase;



}
