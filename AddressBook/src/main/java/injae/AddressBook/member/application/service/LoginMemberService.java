package injae.AddressBook.member.application.service;

import injae.AddressBook.member.application.port.in.LoginMemberUseCase;
import injae.AddressBook.member.application.port.out.FindByEmailRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginMemberService implements LoginMemberUseCase {

    private final FindByEmailRepository findByEmailRepository;

    @Override
    public Member loginMember(String loginId, String password) {
        return findByEmailRepository.findByEmail(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
