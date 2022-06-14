package wwhstudycleanarchproject.no24shop.member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudycleanarchproject.no24shop.member.application.port.out.GetMemberRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaGetMemberRepository implements GetMemberRepository {

    private final EntityManager em;

    @Override
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

}
