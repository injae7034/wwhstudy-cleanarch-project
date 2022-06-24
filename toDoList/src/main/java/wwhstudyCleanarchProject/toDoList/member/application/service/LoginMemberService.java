package wwhstudyCleanarchProject.toDoList.member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudyCleanarchProject.toDoList.member.application.port.in.LoginMemberUseCase;
import wwhstudyCleanarchProject.toDoList.member.application.port.out.FindByEmailRepository;
import wwhstudyCleanarchProject.toDoList.member.domain.Member;

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
