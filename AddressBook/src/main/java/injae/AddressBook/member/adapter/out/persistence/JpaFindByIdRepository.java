package injae.AddressBook.member.adapter.out.persistence;

import injae.AddressBook.member.application.port.out.FindByIdRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaFindByIdRepository implements FindByIdRepository {

    private final EntityManager em;

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

}
