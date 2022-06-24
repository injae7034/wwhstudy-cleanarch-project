package wwhstudyCleanarchProject.toDoList.member.application.port.in;

import wwhstudyCleanarchProject.toDoList.member.domain.Member;

public interface ChangePasswordUseCase {

    Member changePassword(Member member);

}
