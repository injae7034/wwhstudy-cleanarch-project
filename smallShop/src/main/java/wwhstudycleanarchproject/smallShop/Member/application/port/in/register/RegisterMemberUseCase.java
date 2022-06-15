package wwhstudycleanarchproject.smallShop.Member.application.port.in.register;

import wwhstudycleanarchproject.smallShop.Member.domain.Member;

public interface RegisterMemberUseCase {

    Member registerMember(RegisterMemberCommand command);

}
