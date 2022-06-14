package wwhstudycleanarchproject.no24shop.member.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.no24shop.common.CreateAndUpdateTimeAndBy;
import wwhstudycleanarchproject.no24shop.member.application.port.in.join.JoinMemberCommand;
import wwhstudycleanarchproject.no24shop.member.application.port.in.join.JoinMemberUseCase;
import wwhstudycleanarchproject.no24shop.member.application.port.out.JoinMemberRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service
public class JoinMemberService implements JoinMemberUseCase {

    private final JoinMemberRepository repository;

    @Override
    public Long join(JoinMemberCommand command) {
        CreateAndUpdateTimeAndBy timeAndBy = new CreateAndUpdateTimeAndBy(
                LocalDateTime.now(),
                command.getName(),
                null,
                null
        );

        Member member = new Member(
                command.getName(),
                command.getType(),
                command.getPassword(),
                command.getRole(),
                command.getAddress(),
                command.getEmail(),
                timeAndBy
        );

        repository.save(member);

        return member.getId();
    }
}
