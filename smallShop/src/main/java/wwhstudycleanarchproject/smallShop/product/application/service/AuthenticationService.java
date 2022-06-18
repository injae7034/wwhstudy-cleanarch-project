package wwhstudycleanarchproject.smallShop.product.application.service;

import org.springframework.stereotype.Service;
import wwhstudycleanarchproject.smallShop.common.utils.JwtUtil;


@Service
public class AuthenticationService {

    private JwtUtil jwtUtil;

    public AuthenticationService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {
        return jwtUtil.encode(1L);
    }

    public Long parseToken(String accessToken) {
        return null;
    }
}
