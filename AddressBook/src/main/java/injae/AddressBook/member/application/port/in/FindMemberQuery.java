package injae.AddressBook.member.application.port.in;

import injae.AddressBook.member.domain.Member;

public interface FindMemberQuery {

    Member findMember(Long id);

}
