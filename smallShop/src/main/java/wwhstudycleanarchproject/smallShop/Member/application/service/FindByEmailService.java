package wwhstudycleanarchproject.smallShop.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.FindByEmailQuery;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.FindByEmailRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

@RequiredArgsConstructor
@Service
public class FindByEmailService implements FindByEmailQuery {

    private final FindByEmailRepository repository;

    @Override
    public Member findMemberByEmail(String email) {
        return repository.findByEmail(email);
    }
}
