package injae.AddressBook.personal.adapter.out.persistence;

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
    public Personal findOne(Long id) {
        return em.find(Personal.class, id);
    }

}
