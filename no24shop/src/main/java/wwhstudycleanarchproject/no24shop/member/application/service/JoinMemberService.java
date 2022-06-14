package wwhstudycleanarchproject.no24shop.member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.no24shop.member.application.port.in.join.JoinMemberCommand;
import wwhstudycleanarchproject.no24shop.member.application.port.in.join.JoinMemberUseCase;
import wwhstudycleanarchproject.no24shop.member.application.port.out.JoinMemberRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

@RequiredArgsConstructor
@Transactional
@Service
public class JoinMemberService implements JoinMemberUseCase {

    private final JoinMemberRepository repository;

    @Override
    public Long join(JoinMemberCommand command) throws IllegalStateException {

        Member member = new Member(
                command.getEmail(),
                command.getPassword(),
                command.getName(),
                command.getAddress(),
                command.getType(),
                command.getRole()
        );

        repository.save(member);

        return member.getId();
    }

}
