package injae.AddressBook.personal.application.service;

import injae.AddressBook.member.application.port.out.FindByEmailRepository;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.RecordPersonalUseCase;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RecordPersonalService implements RecordPersonalUseCase {

    private final RecordPersonalRepository recordRepository;

    private final FindByEmailRepository findRepository;

    @Override
    public Long recordPersonal(String name, String address, String telephoneNumber,
                               String emailAddress, Member member) {

        Personal personal = new Personal(name, address, telephoneNumber,
                emailAddress, member);

        recordRepository.save(personal);

        Member findMember = findRepository.findByEmail(member.getEmail()).orElse(null);

        if (findMember != null) {
            findMember.getPersonals().add(personal);

            return personal.getId();
        } else {
            throw new IllegalStateException();
        }
    }
}
