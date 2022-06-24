package wwhstudyCleanarchProject.toDoList.Member.application.port.in;

import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

public interface LoginMemberUseCase {

    Member loginMember(String loginId, String password);

}
