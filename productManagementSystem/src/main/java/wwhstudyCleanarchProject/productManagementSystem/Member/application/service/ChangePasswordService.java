package wwhstudyCleanarchProject.productManagementSystem.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.in.ChangePasswordUseCase;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out.ChangePasswordRepository;
import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

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
