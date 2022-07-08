package injae.AddressBook.member.application.service;

import injae.AddressBook.common.exception.DuplicateMemberException;
import injae.AddressBook.member.application.port.in.RegisterMemberUseCase;
import injae.AddressBook.member.application.port.out.FindByEmailRepository;
import injae.AddressBook.member.application.port.out.RegisterMemberRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RegisterMemberService implements RegisterMemberUseCase {

    private final RegisterMemberRepository registerMemberRepository;

    private final FindByEmailRepository findByEmailRepository;

    @Override
    public Long registerMember(Member member) {

        //이미 존재하는 회원 id 여부 확인
        validateDuplicateMember(member);

        //새로운 회원 id이면 DB에 저장함.(회원가입 승인)
        registerMemberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = findByEmailRepository.findByEmail(member.getEmail()).orElse(null);

        if (findMember != null) {
            throw new DuplicateMemberException("이미 존재하는 아이디입니다.");
        }
    }
}
