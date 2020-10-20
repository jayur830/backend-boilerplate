package kr.co.kesti.security;

import lombok.extern.slf4j.Slf4j;
import kr.co.kesti.repository.member.MemberAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Resource(name = "memberAuthRepository")
    private MemberAuthRepository memberAuthRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserDetails user = this.memberAuthRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return user;
    }
}