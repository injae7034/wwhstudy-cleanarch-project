package wwhstudyCleanarchProject.productManagementSystem.Member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out.DeleteMemberRepository;
import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaDeleteMemberRepository implements DeleteMemberRepository {

    private final EntityManager em;

    @Override
    public Member delete(Member member) {

        em.remove(member);

        return member;
    }
}
