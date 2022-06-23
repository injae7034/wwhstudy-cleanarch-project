package wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out;


import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

public interface FindByEmailRepository {

    Member findByEmail(String email);

}
