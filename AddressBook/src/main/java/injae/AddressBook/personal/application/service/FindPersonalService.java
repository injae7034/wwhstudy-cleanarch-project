package injae.AddressBook.personal.application.service;

import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.application.port.in.FindPersonalUseCase;
import injae.AddressBook.personal.application.port.out.FindPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class FindPersonalService implements FindPersonalUseCase {

    private final FindPersonalRepository repository;

    @Override
    public List<Personal> findPersonalByName(Member member, String name) {
        return repository.findByName(member, name);
    }
}
