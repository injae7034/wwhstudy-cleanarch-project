package wwhstudyCleanarchProject.toDoList.member.application.port.out;

import wwhstudyCleanarchProject.toDoList.member.domain.Member;

public interface ChangePasswordRepository {

    Member update(Member member);

}
