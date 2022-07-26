package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.out.GetPersonalsRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaGetPersonalsRepository implements GetPersonalsRepository {

    private final EntityManager em;

    @Override
    public List<Personal> findAll(Member member) {
        return em.createQuery("select p from Personal p where p.member = :member",
                        Personal.class)
                .setParameter("member", member)
                .getResultList();
    }

}
