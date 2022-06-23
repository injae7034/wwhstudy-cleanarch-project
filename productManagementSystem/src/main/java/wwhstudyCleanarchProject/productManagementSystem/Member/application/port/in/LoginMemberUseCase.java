package wwhstudyCleanarchProject.productManagementSystem.Member.application.port.in;

import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

public interface LoginMemberUseCase {

    Member loginMember(String loginId, String password);

}
