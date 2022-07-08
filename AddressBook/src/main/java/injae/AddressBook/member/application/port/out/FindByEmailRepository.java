package injae.AddressBook.member.application.port.out;

import injae.AddressBook.member.domain.Member;

import java.util.Optional;

public interface FindByEmailRepository {

    Optional<Member> findByEmail(String email);

}
