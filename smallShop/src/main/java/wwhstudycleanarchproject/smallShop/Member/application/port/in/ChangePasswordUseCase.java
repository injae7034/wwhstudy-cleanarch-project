package wwhstudycleanarchproject.smallShop.Member.application.port.in;

import wwhstudycleanarchproject.smallShop.Member.domain.Member;

public interface ChangePasswordUseCase {

    Member changePassword(Member member);

}
