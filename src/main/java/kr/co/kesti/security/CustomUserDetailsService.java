package kr.co.kesti.security;

import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Resource(name = "memberRepository")
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Member member = this.memberRepository.findMemberByUsername(username);
        if (member == null) throw new UsernameNotFoundException(username);
        return new CustomUserDetails(username, member.getPassword());
    }
}