package injae.AddressBook.personal.adapter.in.web.home;

import injae.AddressBook.personal.application.port.in.arrange.ArrangePersonalQuery;
import injae.AddressBook.personal.application.port.in.get.GetPersonalsQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPersonalsQuery getQuery;

    @MockBean
    private ArrangePersonalQuery arrangeQuery;

    @Test
    void homeTest() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

        verify(getQuery).getPersonals();

    }

    @Test
    void arrangePersonalTest() throws Exception {

        mockMvc.perform(post("/"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(arrangeQuery).arrangePersonal();

    }
}
