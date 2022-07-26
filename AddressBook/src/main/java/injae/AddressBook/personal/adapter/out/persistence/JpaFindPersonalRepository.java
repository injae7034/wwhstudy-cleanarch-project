package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.out.FindPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaFindPersonalRepository implements FindPersonalRepository {

    private final EntityManager em;

    @Override
    public List<Personal> findByName(Member member, String name) {
        return em.createQuery(
                        "select p from Personal p where p.member = :member and" +
                                " p.name = :name",
                        Personal.class)
                .setParameter("member", member)
                .setParameter("name", name)
                .getResultList();
    }

}
