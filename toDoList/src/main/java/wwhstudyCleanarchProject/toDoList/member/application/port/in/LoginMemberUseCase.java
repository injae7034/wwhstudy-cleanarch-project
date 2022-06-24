package wwhstudyCleanarchProject.toDoList.member.application.port.in;

import wwhstudyCleanarchProject.toDoList.member.domain.Member;

public interface LoginMemberUseCase {

    Member loginMember(String loginId, String password);

}
