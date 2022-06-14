package wwhstudycleanarchproject.no24shop.member.application.port.in.get;

import wwhstudycleanarchproject.no24shop.member.domain.Member;

import java.util.List;

public interface GetAllMembersQuery {

    List<Member> getAllMembers();

}
