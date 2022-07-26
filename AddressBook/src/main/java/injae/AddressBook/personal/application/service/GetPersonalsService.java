package injae.AddressBook.personal.application.service;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.GetPersonalsQuery;
import injae.AddressBook.personal.application.port.out.GetPersonalsRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetPersonalsService implements GetPersonalsQuery {

    private final GetPersonalsRepository repository;

    @Override
    public List<Personal> getPersonals(Member member) {

        return repository.findAll(member);
    }
}
