package injae.AddressBook.personal.application.service;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.GetPersonalQuery;
import injae.AddressBook.personal.application.port.out.GetPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetPersonalService implements GetPersonalQuery {

    private final GetPersonalRepository repository;

    @Override
    public Personal getPersonal(Member member, Long personalId) {
        return repository.findOne(member, personalId);
    }

}
