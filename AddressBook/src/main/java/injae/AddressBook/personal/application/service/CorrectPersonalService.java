package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.correct.CorrectPersonalCommand;
import injae.AddressBook.personal.application.port.in.correct.CorrectPersonalUseCase;
import injae.AddressBook.personal.application.port.out.CorrectPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CorrectPersonalService implements CorrectPersonalUseCase {

    private final CorrectPersonalRepository repository;

    @Override
    public Long correctPersonal(CorrectPersonalCommand command) {
        Personal personal = new Personal(
                command.getId(),
                command.getName(),
                command.getAddress(),
                command.getTelephoneNumber(),
                command.getEmailAddress());

        repository.update(personal);

        return personal.getId();
    }

}
