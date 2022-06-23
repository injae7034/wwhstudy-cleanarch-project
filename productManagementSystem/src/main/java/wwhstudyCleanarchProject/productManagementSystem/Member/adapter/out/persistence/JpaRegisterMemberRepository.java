package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out.RegisterMemberRepository;
import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaRegisterMemberRepository implements RegisterMemberRepository {

    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }
}
