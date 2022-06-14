package wwhstudycleanarchproject.no24shop.member.application.port.in.get;

import wwhstudycleanarchproject.no24shop.member.domain.Member;

public interface GetMemberQuery {

    Member getMember(GetMemberCommand command);

}
