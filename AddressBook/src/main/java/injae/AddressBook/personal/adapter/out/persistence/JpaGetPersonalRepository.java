package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.out.GetPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaGetPersonalRepository implements GetPersonalRepository {

    private final EntityManager em;

    @Override
    public Personal findOne(Member member, Long personalId) {
        return em.createQuery(
                        "Select p from Personal p where p.member = :member and" +
                                " p.id = :personalId",
                        Personal.class)
                .setParameter("member", member)
                .setParameter("personalId", personalId)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);
    }

}
