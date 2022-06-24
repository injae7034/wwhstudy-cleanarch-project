package wwhstudyCleanarchProject.toDoList.member.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wwhstudyCleanarchProject.toDoList.member.application.port.out.ChangePasswordRepository;
import wwhstudyCleanarchProject.toDoList.member.domain.Member;

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
