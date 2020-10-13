package kr.co.kesti.service.impl;

import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.repository.MemberRepository;
import kr.co.kesti.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource(name = "memberRepository")
    private MemberRepository memberRepository;
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void addMember(Member member) {
        String password = member.getPassword();
        member.withPassword(this.passwordEncoder.encode(password));

    }

    @Transactional
    @Override
    public void removeMember(String username) {

    }

    @Override
    public String findUsername(Member member) {
        return null;
    }

    @Override
    public void findPassword(Member member) {

    }
}
