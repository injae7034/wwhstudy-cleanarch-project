package injae.AddressBook.personal.application.port.out;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.domain.Personal;

import java.util.List;

public interface ArrangePersonalRepository {

    List<Personal> arrange(Member member);

}
