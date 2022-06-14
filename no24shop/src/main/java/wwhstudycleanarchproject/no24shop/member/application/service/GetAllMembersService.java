package wwhstudycleanarchproject.no24shop.member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudycleanarchproject.no24shop.member.application.port.in.get.GetAllMembersQuery;
import wwhstudycleanarchproject.no24shop.member.application.port.out.GetAllMembersRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

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
