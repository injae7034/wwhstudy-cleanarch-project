package wwhstudycleanarchproject.smallShop.Member.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import wwhstudycleanarchproject.smallShop.Member.adapter.in.web.register.RegisterMemberController;
import wwhstudycleanarchproject.smallShop.Member.application.port.in.RegisterMemberUseCase;
import wwhstudycleanarchproject.smallShop.Member.application.service.RegisterMemberService;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({RegisterMemberController.class, RegisterMemberService.class})
class RegisterMemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterMemberUseCase useCase;

    @Test
    void registerMemberTest() throws Exception {
        Member member = new Member(
                "tester@example.com",
                "test",
                "Tester",
                "TestAddress"
        );

        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"email\":\"tester@example.com\"," +
                                        "\"password\":\"test\"," +
                                        "\"name\":\"Tester\"," +
                                        "\"address\":\"TestAddress\"}\n")
                )
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"email\":\"tester@example.com\"")));

    }
}
