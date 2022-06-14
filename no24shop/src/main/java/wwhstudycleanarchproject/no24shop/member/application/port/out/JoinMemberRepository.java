package wwhstudycleanarchproject.no24shop.member.application.port.out;

import wwhstudycleanarchproject.no24shop.member.domain.Member;

public interface JoinMemberRepository {

    void save(Member member);

}
