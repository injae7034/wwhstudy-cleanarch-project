package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.ArrangePersonalQuery;
import injae.AddressBook.personal.application.port.out.ArrangePersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArrangePersonalByNameService implements ArrangePersonalQuery {

    private final ArrangePersonalRepository repository;

    @Override
    public List<Personal> arrangePersonal() {
        return repository.arrange();
    }
}
