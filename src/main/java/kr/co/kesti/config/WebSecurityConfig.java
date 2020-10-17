package kr.co.kesti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "authenticationProvider")
    private AuthenticationProvider authenticationProvider;
    @Resource(name = "loginSuccessHandler")
    private AuthenticationSuccessHandler loginSuccessHandler;
    @Resource(name = "loginFailureHandler")
    private AuthenticationFailureHandler loginFailurehandler;
    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 패스워드 암호화 알고리즘
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 정적 자원 경로는 무시
     * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/font/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 접근 권한 설정
         * */
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .antMatchers("/main/**").hasRole("ROLE_USER")
                .antMatchers("/member/**").permitAll()
                .antMatchers("/**").permitAll();

        /**
         * 인증
         * */
        http.authenticationProvider(this.authenticationProvider)
                .userDetailsService(this.userDetailsService);

        /**
         * 로그인 설정
         * */
        http.formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/auth/login")

                /**
                 * USERNAME 파라미터 이름 변경 필요
                 * */
                .usernameParameter("username")

                /**
                 * PASSWORD 파라미터 이름 변경 필요
                 * */
                .passwordParameter("password")
                .successHandler(this.loginSuccessHandler)
                .failureHandler(this.loginFailurehandler);

        /**
         * 로그아웃 설정
         * */
        http.logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/member/login")
                .invalidateHttpSession(true);

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll();

        /**
         * 세션 설정
         * */
        http.sessionManagement()
                .invalidSessionUrl("/member/login")
                .maximumSessions(1)
                .expiredUrl("/member/login")
                .maxSessionsPreventsLogin(true);
    }
}