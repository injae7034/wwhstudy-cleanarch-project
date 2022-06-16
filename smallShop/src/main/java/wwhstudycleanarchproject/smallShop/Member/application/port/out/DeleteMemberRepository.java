package wwhstudycleanarchproject.smallShop.Member.application.port.out;

import wwhstudycleanarchproject.smallShop.Member.domain.Member;

public interface DeleteMemberRepository {

    Member delete(Member member);

}
