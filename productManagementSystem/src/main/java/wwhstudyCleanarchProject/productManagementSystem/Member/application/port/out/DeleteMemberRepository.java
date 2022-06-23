package wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out;


import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

public interface DeleteMemberRepository {

    Member delete(Member member);

}
