package kr.co.kesti.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component("authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;
    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();
        final UserDetails user = this.userDetailsService.loadUserByUsername(username);

        // 아이디 또는 비밀번호가 일치하지 않는 경우
        if (!user.getUsername().equals(username) ||
                !this.passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException(username);
        // 비밀번호 유효기간이 만료된 경우
        else if (!user.isCredentialsNonExpired()) throw new CredentialsExpiredException(username);
        // 해당 계정이 비활성화된 경우
        else if (!user.isEnabled()) throw new DisabledException(username);
        // 해당 계정이 만료된 경우
        else if (!user.isAccountNonExpired()) throw new AccountExpiredException(username);
        // 해당 계정이 잠긴 경우
        else if (!user.isAccountNonLocked()) throw new LockedException(username);

        // 아이디, 비밀번호, 권한 정보에 대한 인증 토큰 반환                                                                                                                                                                                                                                                                        이라고는 써놨는데 솔직히 나도 잘 모름
        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}