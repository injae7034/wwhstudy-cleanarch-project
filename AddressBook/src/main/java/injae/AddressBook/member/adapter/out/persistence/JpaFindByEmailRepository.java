package injae.AddressBook.member.adapter.out.persistence;

import injae.AddressBook.member.application.port.out.FindByEmailRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaFindByEmailRepository implements FindByEmailRepository {

    private final EntityManager em;

    @Override
    public Optional<Member> findByEmail(String email) {
        return em.createQuery(
                        "select m from Member m where m.email = :email",
                        Member.class)
                .setParameter("email", email)
                .getResultStream().findAny();
    }
}
