package wwhstudyCleanarchProject.toDoList.member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.toDoList.member.application.port.in.ChangePasswordUseCase;
import wwhstudyCleanarchProject.toDoList.member.application.port.out.ChangePasswordRepository;
import wwhstudyCleanarchProject.toDoList.member.domain.Member;

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
