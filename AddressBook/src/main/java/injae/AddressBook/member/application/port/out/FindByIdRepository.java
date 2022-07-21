package injae.AddressBook.member.application.port.out;

import injae.AddressBook.member.domain.Member;

import java.util.Optional;

public interface FindByIdRepository {

    Optional<Member> findById(Long id);

}
