package wwhstudycleanarchproject.smallShop.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.RegisterMemberUseCase;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.RegisterMemberRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

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
