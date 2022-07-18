package injae.AddressBook.member.application.port.in;

import injae.AddressBook.member.domain.Member;

public interface RegisterMemberUseCase {

    Long registerMember(String email, String password, String name);
}
