package wwhstudyCleanarchProject.productManagementSystem.Member.application.port.out;


import wwhstudyCleanarchProject.productManagementSystem.Member.domain.Member;

import java.util.Optional;

public interface FindByEmailRepository {

    Optional<Member> findByEmail(String email);

}
