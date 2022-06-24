package wwhstudyCleanarchProject.toDoList.Member.application.port.out;

import wwhstudyCleanarchProject.toDoList.Member.domain.Member;

import java.util.Optional;

public interface FindByEmailRepository {

    Optional<Member> findByEmail(String email);

}
