package wwhstudycleanarchproject.smallShop.Member.application.port.in;

import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import java.util.List;

public interface GetAllMembersQuery {

    List<Member> getAllMembers();

}
