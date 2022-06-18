package wwhstudycleanarchproject.smallShop.Member.adapter.in.web.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SessionResponseData {
    private String accessToken;
}
