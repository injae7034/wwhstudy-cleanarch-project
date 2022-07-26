package injae.AddressBook.personal.application.port.in;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.domain.Personal;

import java.util.List;

public interface GetPersonalsQuery {

    List<Personal> getPersonals(Member member);

}
