package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.domain.Personal;

public interface GetPersonalRepository {

    Personal findOne(Member member, Long personalId);

}
