package wwhstudyCleanarchProject.toDoList.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.toDoList.Member.application.port.in.DeleteMemberUseCase;
import wwhstudyCleanarchProject.toDoList.Member.application.port.out.DeleteMemberRepository;
import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

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
