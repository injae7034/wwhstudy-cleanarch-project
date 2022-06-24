package wwhstudyCleanarchProject.toDoList.member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.member.application.port.out.DeleteMemberRepository;
import wwhstudyCleanarchProject.toDoList.member.domain.Member;

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
