package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.PersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaPersonalRepository implements PersonalRepository {

    private final EntityManager em;

    @Override
    public void save(Personal personal) {
        em.persist(personal);
    }

    @Override
    public Personal findOne(Long id) {
        return em.find(Personal.class, id);
    }

    @Override
    public List<Personal> findAll() {
        return em.createQuery("select p from Personal p", Personal.class)
                .getResultList();
    }

    @Override
    public List<Personal> findByName(String name) {
        return em.createQuery("select p from Personal p where p.name = :name",
                        Personal.class)
                .setParameter("name", name)
                .getResultList();
    }
}
