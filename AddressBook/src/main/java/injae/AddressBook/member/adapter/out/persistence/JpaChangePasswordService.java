package injae.AddressBook.member.adapter.out.persistence;

import injae.AddressBook.member.application.port.out.ChangePasswordRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaChangePasswordService implements ChangePasswordRepository {

    private final EntityManager em;

    @Override
    public void update(Long memberId, String password) {
        Member findMember = em.find(Member.class, memberId);

        findMember.changePassword(password);
    }
}
