package wwhstudycleanarchproject.smallShop.Member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.GetAllMembersRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaGetAllMembersRepository implements GetAllMembersRepository {

    private final EntityManager em;

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
