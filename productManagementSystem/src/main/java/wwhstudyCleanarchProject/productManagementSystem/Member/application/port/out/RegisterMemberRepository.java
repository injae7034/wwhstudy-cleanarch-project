package wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out;


import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

public interface RegisterMemberRepository {

    Member save(Member member);

}
