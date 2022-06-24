package wwhstudyCleanarchProject.toDoList.Member.application.port.out;

import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

public interface DeleteMemberRepository {

    Member delete(Member member);

}
