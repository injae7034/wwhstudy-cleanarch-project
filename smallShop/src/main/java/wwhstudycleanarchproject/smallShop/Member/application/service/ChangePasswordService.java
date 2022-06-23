package wwhstudycleanarchproject.smallShop.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.ChangePasswordUseCase;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.ChangePasswordRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

@RequiredArgsConstructor
@Transactional
@Service
public class ChangePasswordService implements ChangePasswordUseCase {

    private final ChangePasswordRepository repository;

    @Override
    public Member changePassword(Member member) {
        return repository.update(member);
    }
}
