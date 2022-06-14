package wwhstudycleanarchproject.no24shop.member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudycleanarchproject.no24shop.member.application.port.out.GetAllMembersRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

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
