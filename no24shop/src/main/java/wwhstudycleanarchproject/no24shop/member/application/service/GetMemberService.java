package wwhstudycleanarchproject.no24shop.member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwhstudycleanarchproject.no24shop.member.application.port.in.get.GetMemberCommand;
import wwhstudycleanarchproject.no24shop.member.application.port.in.get.GetMemberQuery;
import wwhstudycleanarchproject.no24shop.member.application.port.out.GetMemberRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

@RequiredArgsConstructor
@Service
public class GetMemberService implements GetMemberQuery {

    private final GetMemberRepository repository;

    @Override
    public Member getMember(GetMemberCommand command) {
        return repository.findOne(command.getId());
    }
}
