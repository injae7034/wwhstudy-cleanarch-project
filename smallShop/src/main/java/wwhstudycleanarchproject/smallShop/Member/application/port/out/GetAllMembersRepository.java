package wwhstudycleanarchproject.smallShop.Member.application.port.out;

import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import java.util.List;

public interface GetAllMembersRepository {

    List<Member> findAll();

}
