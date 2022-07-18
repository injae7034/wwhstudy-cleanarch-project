package injae.AddressBook.member.application.port.in;

import injae.AddressBook.member.domain.Member;

public interface LoginMemberUseCase {

    Member loginMember(String loginId, String password);

}
