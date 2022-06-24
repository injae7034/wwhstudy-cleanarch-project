package wwhstudyCleanarchProject.toDoList.Member.application.port.out;

import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

public interface ChangePasswordRepository {

    Member update(Member member);

}
