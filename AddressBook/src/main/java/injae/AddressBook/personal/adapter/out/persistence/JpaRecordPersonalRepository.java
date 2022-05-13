package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaRecordPersonalRepository implements RecordPersonalRepository {

    private final EntityManager em;

    @Override
    public void save(Personal personal) {
        em.persist(personal);
    }

}
