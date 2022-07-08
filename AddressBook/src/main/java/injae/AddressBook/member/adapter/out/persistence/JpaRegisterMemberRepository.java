package injae.AddressBook.member.adapter.out.persistence;

import injae.AddressBook.member.application.port.out.RegisterMemberRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaRegisterMemberRepository implements RegisterMemberRepository {

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }
}
