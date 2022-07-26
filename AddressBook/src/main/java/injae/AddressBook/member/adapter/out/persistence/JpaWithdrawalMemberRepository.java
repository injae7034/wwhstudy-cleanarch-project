package injae.AddressBook.member.adapter.out.persistence;

import injae.AddressBook.member.application.port.out.WithdrawalMemberRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaWithdrawalMemberRepository implements WithdrawalMemberRepository {

    private final EntityManager em;

    @Override
    public void delete(Member member) {
        em.remove(member);
    }
}
