package kr.co.kesti.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        request.getSession().removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        /**
         * 로그인 성공 시 후처리
         * */

        response.getWriter().print("{ \"isSuccess\": true }");
    }
}