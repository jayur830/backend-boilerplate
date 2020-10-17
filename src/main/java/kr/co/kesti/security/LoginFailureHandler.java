package kr.co.kesti.security;

import kr.co.kesti.utils.LocaleUtil;
import kr.co.kesti.define.AuthStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        final String username = request.getParameter("username");
        String errorMessage = "", error = "";

        // 아이디 또는 비밀번호를 잘못 입력한 경우
        if (exception instanceof BadCredentialsException)
            errorMessage = LocaleUtil.getMessage("error." + (error = AuthStatus.BAD_CREDENTIALS));
        // 비밀번호 유효기간이 만료된 경우
        else if (exception instanceof CredentialsExpiredException)
            errorMessage = LocaleUtil.getMessage("error." + (error = AuthStatus.CREDENTIALS_EXPIRED));
        // 해당 계정이 비활성화된 경우
        else if (exception instanceof DisabledException)
            errorMessage = LocaleUtil.getMessage("error." + (error = AuthStatus.DISABLED));
        // 해당 계정이 만료된 경우
        else if (exception instanceof AccountExpiredException)
            errorMessage = LocaleUtil.getMessage("error." + (error = AuthStatus.ACCOUNT_EXPIRED));
        // 해당 계정이 잠긴 경우
        else if (exception instanceof LockedException)
            errorMessage = LocaleUtil.getMessage("error." + (error = AuthStatus.LOCKED));

        postProcess(username, error);

        response.setCharacterEncoding("utf-8");
        response.getWriter().write(String.format("{ \"isSuccess\": false, \"errorMssage\": %s }", errorMessage));
    }

    /**
     * 로그인 실패 시 후처리 작업
     * */
    private void postProcess(final String username, final String error) {
        switch (error) {
            // 아이디 또는 비밀번호를 잘못 입력한 경우
            case AuthStatus.BAD_CREDENTIALS:
                /**
                 * 로직 작성
                 * */
                break;
            // 비밀번호 유효기간이 만료된 경우
            case AuthStatus.CREDENTIALS_EXPIRED:
                /**
                 * 로직 작성
                 * */
                break;
            // 해당 계정이 비활성화된 경우
            case AuthStatus.DISABLED:
                /**
                 * 로직 작성
                 * */
                break;
            // 해당 계정이 만료된 경우
            case AuthStatus.ACCOUNT_EXPIRED:
                /**
                 * 로직 작성
                 * */
                break;
            // 해당 계정이 잠긴 경우
            case AuthStatus.LOCKED:
                /**
                 * 로직 작성
                 * */
                break;
        }
    }
}