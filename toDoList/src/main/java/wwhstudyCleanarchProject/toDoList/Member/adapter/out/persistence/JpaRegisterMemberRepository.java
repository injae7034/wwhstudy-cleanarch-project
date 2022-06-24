package wwhstudyCleanarchProject.toDoList.Member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.Member.application.port.out.RegisterMemberRepository;
import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

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
