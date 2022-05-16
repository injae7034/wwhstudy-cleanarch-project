package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.find.FindPersonalCommand;
import injae.AddressBook.personal.application.port.in.get.GetPersonalCommand;
import injae.AddressBook.personal.application.port.in.get.GetPersonalQuery;
import injae.AddressBook.personal.application.port.out.GetPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetPersonalService implements GetPersonalQuery {

    private final GetPersonalRepository repository;

    @Override
    public Personal getPersonal(GetPersonalCommand command) {
        return repository.findOne(command.getId());
    }

}
