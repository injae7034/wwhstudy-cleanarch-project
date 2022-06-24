package wwhstudyCleanarchProject.toDoList.Member.application.port.out;

import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

public interface RegisterMemberRepository {

    Member save(Member member);

}
