package injae.AddressBook.member.application.service;

import injae.AddressBook.member.application.port.in.ChangePasswordUseCase;
import injae.AddressBook.member.application.port.out.ChangePasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChangePasswordService implements ChangePasswordUseCase {

    private final ChangePasswordRepository repository;

    @Override
    public void changePassword(Long memberId, String changePassword) {
        repository.update(memberId, changePassword);

    }
}
