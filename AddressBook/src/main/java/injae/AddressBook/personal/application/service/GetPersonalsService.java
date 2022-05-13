package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.get.GetPersonalsUseCase;
import injae.AddressBook.personal.application.port.out.GetPersonalsRepository;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetPersonalsService implements GetPersonalsUseCase {

    private final GetPersonalsRepository repository;

    @Override
    public List<Personal> getPersonals() {
        return repository.findAll();
    }
}
