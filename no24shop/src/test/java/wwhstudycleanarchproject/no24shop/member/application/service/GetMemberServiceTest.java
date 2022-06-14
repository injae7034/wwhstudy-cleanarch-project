package wwhstudycleanarchproject.no24shop.member.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.no24shop.member.application.port.in.get.GetMemberCommand;
import wwhstudycleanarchproject.no24shop.member.application.port.in.get.GetMemberQuery;
import wwhstudycleanarchproject.no24shop.member.application.port.out.GetMemberRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetMemberServiceTest {

    private final GetMemberRepository repository =
            mock(GetMemberRepository.class);

    private final GetMemberQuery query =
            new GetMemberService(repository);

    private final Long ANY_ID = 1L;

    @Test
    void getMemberTest() {
        //given & then
        query.getMember(new GetMemberCommand(ANY_ID));

        //then
        verify(repository).findOne(ANY_ID);
    }

}
