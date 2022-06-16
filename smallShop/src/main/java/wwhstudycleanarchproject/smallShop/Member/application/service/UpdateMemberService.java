package wwhstudycleanarchproject.smallShop.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.UpdateMemberUseCase;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.UpdateMemberRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateMemberService implements UpdateMemberUseCase {

    private final UpdateMemberRepository repository;

    @Override
    public Member updateMember(Member member) {
        return repository.update(member);
    }
}
