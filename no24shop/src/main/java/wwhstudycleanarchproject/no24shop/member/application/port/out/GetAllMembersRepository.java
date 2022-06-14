package wwhstudycleanarchproject.no24shop.member.application.port.out;

import wwhstudycleanarchproject.no24shop.member.domain.Member;

import java.util.List;

public interface GetAllMembersRepository {

    List<Member> findAll();

}
