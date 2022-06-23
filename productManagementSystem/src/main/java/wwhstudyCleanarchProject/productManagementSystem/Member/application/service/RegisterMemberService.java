package wwhstudyCleanarchProject.productManagementSystem.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.in.RegisterMemberUseCase;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out.RegisterMemberRepository;
import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

@RequiredArgsConstructor
@Transactional
@Service
public class RegisterMemberService implements RegisterMemberUseCase {

    private final RegisterMemberRepository repository;

    @Override
    public Member registerMember(Member member) {
        return repository.save(member);
    }
}
