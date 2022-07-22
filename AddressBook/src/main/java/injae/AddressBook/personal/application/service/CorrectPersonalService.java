package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.CorrectPersonalUseCase;
import injae.AddressBook.personal.application.port.out.CorrectPersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CorrectPersonalService implements CorrectPersonalUseCase {

    private final CorrectPersonalRepository repository;

    @Override
    public Long correctPersonal(Long id, String address,
                                String telephoneNumber, String emailAddress) {
        repository.update(id, address, telephoneNumber, emailAddress);

        return id;
    }

}
