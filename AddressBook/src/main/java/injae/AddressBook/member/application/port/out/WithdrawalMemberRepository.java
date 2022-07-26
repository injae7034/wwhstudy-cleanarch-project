package injae.AddressBook.member.application.port.out;

import injae.AddressBook.member.domain.Member;

public interface WithdrawalMemberRepository {

    void delete(Member member);

}
