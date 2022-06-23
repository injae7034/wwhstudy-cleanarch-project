package wwhstudyCleanarchProject.productManagementSystem.Member.application.port.in;


import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

public interface ChangePasswordUseCase {

    Member changePassword(Member member);

}
