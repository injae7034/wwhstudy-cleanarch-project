package injae.AddressBook;

import injae.AddressBook.common.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/member/register", "/member/login",
                        "/css/**", "/*.ico", "/error")//서버 사이드 렌더링쪽
                .excludePathPatterns("/members", "/members/{id}", "/members/login",
                        "/members/{memberId}/personals",
                        "/members/{memberId}/personals/{personalId}",
                        "/members/{memberId}/personals/find");//api 쪽
    }
}
