package wwhstudycleanarchproject.smallShop.Member.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.register.RegisterMemberCommand;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.register.RegisterMemberUseCase;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.RegisterMemberRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RegisterMemberServiceTest {

    private final RegisterMemberRepository repository =
            mock(RegisterMemberRepository.class);

    private final RegisterMemberUseCase useCase =
            new RegisterMemberService(repository);

    @Test
    void registerMemberTest() {
        //given & when
        useCase.registerMember(new RegisterMemberCommand(
                "hong@naver.com",
                "123456",
                "홍길동",
                "서울시 중구"
        ));

        //then
        verify(repository).save(any(Member.class));

    }
}
