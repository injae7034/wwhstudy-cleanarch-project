package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.ErasePersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaErasePersonalRepository implements ErasePersonalRepository {

    private final EntityManager em;

    @Override
    public void delete(Personal personal) {
        em.remove(personal);
    }
}
