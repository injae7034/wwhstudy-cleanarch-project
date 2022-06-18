package wwhstudycleanarchproject.smallShop.product.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wwhstudycleanarchproject.smallShop.common.utils.JwtUtil;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationServiceTest {
    private static final String SECRET = "12345678901234567890123456789012";

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        JwtUtil jwtUtil = new JwtUtil(SECRET);
        authenticationService = new AuthenticationService(jwtUtil);
    }

    @Test
    void loginTest() {
//        String accessToken = authenticationService.login();
//
//        assertThat(accessToken).contains(".xxx");
    }

}
