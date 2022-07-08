package injae.AddressBook.member.application.port.out;

import injae.AddressBook.member.domain.Member;

public interface RegisterMemberRepository {

    void save(Member member);

}
