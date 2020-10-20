package kr.co.kesti.service.impl;

import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.repository.member.MemberAuthRepository;
import kr.co.kesti.repository.member.MemberRepository;
import kr.co.kesti.security.CustomUserDetails;
import kr.co.kesti.service.MemberService;
import kr.co.kesti.utils.CryptoUtils;
import kr.co.kesti.utils.MailUtils;
import kr.co.kesti.utils.ResourceUtils;
import kr.co.kesti.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;
    @Resource(name = "memberRepository")
    private MemberRepository memberRepository;
    @Resource(name = "memberAuthRepository")
    private MemberAuthRepository memberAuthRepository;

    @Override
    public boolean isExistUser(final String username) {
        return this.memberRepository.countByUsername(username) != 0;
    }

    @Transactional
    @Override
    public void signUp(MemberVO memberVO) {
        memberVO.setPassword(this.passwordEncoder.encode(memberVO.getPassword()));
        Member member = memberVO.toMember();
        CustomUserDetails memberAuth = memberVO.toMemberAuth();
        this.memberRepository.save(member);
        this.memberAuthRepository.save(memberAuth);
    }

    @Override
    public Member findUsername(final String nickname, final String email) {
        return this.memberRepository.findByNicknameAndEmail(nickname, email);
    }

    @Transactional
    @Override
    public void resetPassword(final String username, final String email) {
        final String newPassword = RandomStringUtils.randomAlphanumeric(10);

        String emailContents = ResourceUtils.readText("/templates/WEB-INF/views/templates/findPasswordHTML.html");
        emailContents = emailContents.replace("${newPassword}", newPassword);
        MailUtils.sendEmail(
                "aimakers@gmail.com",
                email,
                "[AIMakers] 임시 비밀번호 안내",
                emailContents,
                true);

        CustomUserDetails memberAuth = this.memberAuthRepository.findByUsername(username);
        memberAuth.setPassword(this.passwordEncoder.encode(CryptoUtils.encryptSHA256(newPassword)));
        this.memberAuthRepository.save(memberAuth);
    }
}