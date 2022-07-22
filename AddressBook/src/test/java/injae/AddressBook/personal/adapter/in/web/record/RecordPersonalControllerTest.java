package injae.AddressBook.personal.adapter.in.web.record;

import com.fasterxml.jackson.databind.ObjectMapper;
import injae.AddressBook.common.PersonalTestData;
import injae.AddressBook.personal.application.port.in.RecordPersonalUseCase;
import injae.AddressBook.personal.domain.Personal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecordPersonalController.class)
class RecordPersonalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordPersonalUseCase useCase;

    @Test
    void recordPersonalTest() throws Exception {

        Personal personal = PersonalTestData.defaultPersonal().build();

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/record"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());


//        verify(useCase).recordPersonal(any());
    }
}
