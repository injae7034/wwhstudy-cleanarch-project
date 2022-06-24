package wwhstudyCleanarchProject.toDoList.member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudyCleanarchProject.toDoList.member.application.port.in.RegisterMemberUseCase;
import wwhstudyCleanarchProject.toDoList.member.application.port.out.FindByEmailRepository;
import wwhstudyCleanarchProject.toDoList.member.application.port.out.RegisterMemberRepository;
import wwhstudyCleanarchProject.toDoList.member.domain.Member;

@RequiredArgsConstructor
@Transactional
@Service
public class RegisterMemberService implements RegisterMemberUseCase {

    private final RegisterMemberRepository registerRepository;

    private final FindByEmailRepository findRepository;

    @Override
    public Member registerMember(Member member) throws IllegalStateException {

        //이미 존재하는 회원인지 확인
        validateDuplicateMember(member);

        //새로운 회원이면 저장함.
        return registerRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = findRepository.findByEmail(member.getEmail()).orElse(null);

        if (findMember != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }


}
