package wwhstudyCleanarchProject.toDoList.Member.application.port.in;

import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

public interface ChangePasswordUseCase {

    Member changePassword(Member member);

}
