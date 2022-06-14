package wwhstudycleanarchproject.no24shop.member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudycleanarchproject.no24shop.member.application.port.out.JoinMemberRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaJoinMemberRepository implements JoinMemberRepository {

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }
}
