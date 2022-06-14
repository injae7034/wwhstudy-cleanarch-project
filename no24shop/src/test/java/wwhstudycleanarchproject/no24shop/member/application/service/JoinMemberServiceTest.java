package wwhstudycleanarchproject.no24shop.member.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.no24shop.domain.Address;
import wwhstudycleanarchproject.no24shop.member.application.port.in.join.JoinMemberCommand;
import wwhstudycleanarchproject.no24shop.member.application.port.in.join.JoinMemberUseCase;
import wwhstudycleanarchproject.no24shop.member.application.port.out.JoinMemberRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class JoinMemberServiceTest {

    private final JoinMemberRepository repository =
            mock(JoinMemberRepository.class);

    private final JoinMemberUseCase useCase = new JoinMemberService(repository);

    @Test
    void joinTest() {
        //given & when
        useCase.join(new JoinMemberCommand(
                "홍길동",
                "GENERAL",
                "123456",
                "USER",
                new Address("서울시", "중구", "123"),
                "hong@naver.com"));

        //then
        verify(repository).save(any(Member.class));
    }

}
