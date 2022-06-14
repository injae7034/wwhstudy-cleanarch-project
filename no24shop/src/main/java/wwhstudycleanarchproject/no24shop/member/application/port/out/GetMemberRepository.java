package wwhstudycleanarchproject.no24shop.member.application.port.out;

import wwhstudycleanarchproject.no24shop.member.domain.Member;

public interface GetMemberRepository {

    Member findOne(Long id);

}
