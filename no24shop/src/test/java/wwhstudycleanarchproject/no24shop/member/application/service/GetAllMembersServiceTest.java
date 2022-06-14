package wwhstudycleanarchproject.no24shop.member.application.service;

import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.no24shop.member.application.port.in.get.GetAllMembersQuery;
import wwhstudycleanarchproject.no24shop.member.application.port.out.GetAllMembersRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetAllMembersServiceTest {

    private final GetAllMembersRepository repository =
            mock(GetAllMembersRepository.class);

    private final GetAllMembersQuery query =
            new GetAllMembersService(repository);

    @Test
    void getAllMembersTest() {
        //given & when
        query.getAllMembers();

        //then
        verify(repository).findAll();

    }
}
