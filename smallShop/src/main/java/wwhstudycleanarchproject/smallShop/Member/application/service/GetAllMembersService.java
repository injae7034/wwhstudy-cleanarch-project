package wwhstudycleanarchproject.smallShop.Member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.GetAllMembersQuery;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.GetAllMembersRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllMembersService implements GetAllMembersQuery {

    private final GetAllMembersRepository repository;

    @Override
    public List<Member> getAllMembers() {
        return repository.findAll();
    }
}
