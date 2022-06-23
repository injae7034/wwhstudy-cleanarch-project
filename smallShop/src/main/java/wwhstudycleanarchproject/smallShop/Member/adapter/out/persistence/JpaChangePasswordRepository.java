package wwhstudycleanarchproject.smallShop.Member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.ChangePasswordRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaChangePasswordRepository implements ChangePasswordRepository {

    private final EntityManager em;

    @Override
    public Member update(Member member) {
        Member findMember = em.find(Member.class, member.getId());

        findMember.changePassword(member.getPassword());

        return findMember;
    }
}
