package injae.AddressBook.personal.application.service;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.record.RecordPersonalUseCase;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RecordPersonalService implements RecordPersonalUseCase {

    private final RecordPersonalRepository repository;

    @Override
    public Long recordPersonal(String name, String address, String telephoneNumber,
                               String emailAddress, Member member) {

        Personal personal = new Personal(name, address, telephoneNumber,
                emailAddress, member);

        member.getPersonals().add(personal);

        repository.save(personal);

        return personal.getId();
    }
}
