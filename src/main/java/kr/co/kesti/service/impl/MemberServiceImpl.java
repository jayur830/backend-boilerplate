package kr.co.kesti.service.impl;

import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.repository.member.MemberRepository;
import kr.co.kesti.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource(name = "memberRepository")
    private MemberRepository memberRepository;
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void addMember(Member member) {
        this.memberRepository.save(member.withPassword(this.passwordEncoder.encode(member.getPassword())));
    }

    @Transactional
    @Override
    public void removeMember(final String username) {
        this.memberRepository.removeMemberByUsername(username);
    }

    @Override
    public String findUsername(Member member) {
        return this.memberRepository.findMemberByMemberInfo(member.getMemberInfo()).getUsername();
    }

    @Override
    public void findPassword(Member member) {

    }
}