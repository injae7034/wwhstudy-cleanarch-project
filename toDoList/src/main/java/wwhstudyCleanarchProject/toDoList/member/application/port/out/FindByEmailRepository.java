package wwhstudyCleanarchProject.toDoList.member.application.port.out;

import wwhstudyCleanarchProject.toDoList.member.domain.Member;

import java.util.Optional;

public interface FindByEmailRepository {

    Optional<Member> findByEmail(String email);

}
