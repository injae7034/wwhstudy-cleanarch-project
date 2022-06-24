package wwhstudyCleanarchProject.toDoList.Member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.Member.application.port.out.DeleteMemberRepository;
import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

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
