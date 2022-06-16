package wwhstudycleanarchproject.smallShop.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.DeleteMemberUseCase;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.DeleteMemberRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteMemberService implements DeleteMemberUseCase {

    private final DeleteMemberRepository repository;

    @Override
    public Member deleteMember(Member member) {
        return repository.delete(member);
    }
}
