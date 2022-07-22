package injae.AddressBook.personal.application.port.in;

import injae.AddressBook.member.domain.Member;

public interface RecordPersonalUseCase {

    Long recordPersonal(String name, String address, String telephoneNumber,
                        String emailAddress, Member member);

}
