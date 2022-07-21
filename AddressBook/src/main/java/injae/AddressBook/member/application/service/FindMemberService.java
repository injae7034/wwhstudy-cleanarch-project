package injae.AddressBook.member.application.service;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.application.port.out.FindByIdRepository;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindMemberService implements FindMemberQuery {

    private final FindByIdRepository repository;

    @Override
    public Member findMember(Long id) {
        return repository.findById(id).orElse(null);
    }
}
