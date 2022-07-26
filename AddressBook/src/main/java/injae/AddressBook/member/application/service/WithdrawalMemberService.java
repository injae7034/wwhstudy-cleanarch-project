package injae.AddressBook.member.application.service;

import injae.AddressBook.member.application.port.in.WithdrawalMemberUseCase;
import injae.AddressBook.member.application.port.out.WithdrawalMemberRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class WithdrawalMemberService implements WithdrawalMemberUseCase {

    private final WithdrawalMemberRepository repository;

    @Override
    public void withdrawalMember(Member member) {
        repository.delete(member);
    }
}
