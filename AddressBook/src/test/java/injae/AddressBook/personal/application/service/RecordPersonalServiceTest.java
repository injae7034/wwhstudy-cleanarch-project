package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.record.RecordPersonalUseCase;
import injae.AddressBook.personal.application.port.out.RecordPersonalRepository;
import injae.AddressBook.personal.domain.Personal;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;


class RecordPersonalServiceTest {

    private final RecordPersonalRepository repository =
            mock(RecordPersonalRepository.class);

//    private final RecordPersonalUseCase useCase = new RecordPersonalService(repository);

    @Test
    void recordPersonalTest() {

        //given & when
//        useCase.recordPersonal(new RecordPersonalCommand(
//                "홍길동",
//                "서울시 중구",
//                "023349090",
//                "hong@naver.com"
//        ));

        //then
        verify(repository).save(any(Personal.class));

//        Personal personal = PersonalTestData.defaultPersonal().build();
//
//        RecordPersonalCommand recordCommand = new RecordPersonalCommand(
//                personal.getName(),
//                personal.getAddress(),
//                personal.getTelephoneNumber(),
//                personal.getEmailAddress()
//        );
//
//        //when
//        Long newId = 1L;
//        Personal newPersonal = new Personal(
//                newId,
//                personal.getName(),
//                personal.getAddress(),
//                personal.getTelephoneNumber(),
//                personal.getEmailAddress()
//        );
//
//        given(useCase.recordPersonal(recordCommand)).willReturn(newId);
//
//        Long id = useCase.recordPersonal(recordCommand);
//
//        verify(repository).save(any(Personal.class));
//        assertThat(id).isEqualTo(newId);
//        doNothing().when(repository).save(newPersonal);

//        doAnswer(invocation -> {
//            Personal value = invocation.getArgument(0);
////            value.setId(newId);
//            Personal newPersonal = new Personal(
//                    newId,
//                    personal.getName(),
//                    personal.getAddress(),
//                    personal.getTelephoneNumber(),
//                    personal.getEmailAddress()
//            );
//            return newPersonal;
//        }).when(repository).save(any(Personal.class));

//        Answer<Personal> answer = new Answer<Personal>() {
//            public Personal answer(InvocationOnMock invocation) throws Throwable {
//                return  new Personal(
//                        newId,
//                        personal.getName(),
//                        personal.getAddress(),
//                        personal.getTelephoneNumber(),
//                        personal.getEmailAddress()
//                );
//            }
//        };
//        doAnswer(answer).when(repository).save(any(Personal.class));
//
//
//        then(repository).should()
//                .save(any(Personal.class));

//        when(repository).save(any(Personal.class)).will();
//
//        given(repository.save(any(Personal.class)))
//                .will(invocation -> {
//                    Long id = invocation.getArgument(0);
//                    Task source = invocation.getArgument(1);
//                    return new Task(
//                            id,
//                            source.getTitle()
//                    );
//                });





//        assertThat(id).isEqualTo(newId);
//        GetPersonalCommand getCommand = new GetPersonalCommand(saveId);

        //then
//        assertThat(saveId).isNotNull();
//        assertThat(personal.getName())
//                .isEqualTo(query.getPersonal(getCommand).getName());
//        assertThat(personal.getAddress())
//                .isEqualTo(query.getPersonal(getCommand).getAddress());
//        assertThat(personal.getTelephoneNumber())
//                .isEqualTo(query.getPersonal(getCommand).getTelephoneNumber());
//        assertThat(personal.getEmailAddress())
//                .isEqualTo(query.getPersonal(getCommand).getEmailAddress());

    }

}
