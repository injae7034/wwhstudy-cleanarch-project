package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.ErasePersonalUseCase;
import injae.AddressBook.personal.application.port.out.ErasePersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ErasePersonalService implements ErasePersonalUseCase {

    private final ErasePersonalRepository repository;

    @Override
    public void erasePersonal(Personal personal) {
        repository.delete(personal);
    }
}
