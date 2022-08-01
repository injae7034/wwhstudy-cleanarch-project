package injae.AddressBook.personal.application.port.in;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.domain.Personal;

public interface GetPersonalQuery {

    Personal getPersonal(Member member, Long personalId);

}
