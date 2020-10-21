package kr.co.kesti.service.impl;

import kr.co.kesti.domain.embed.MemberInfo;
import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.domain.entity.MemberAuth;
import kr.co.kesti.repository.member.MemberAuthRepository;
import kr.co.kesti.repository.member.MemberRepository;
import kr.co.kesti.service.MemberService;
import kr.co.kesti.utils.CryptoUtils;
import kr.co.kesti.utils.MailUtils;
import kr.co.kesti.utils.MessageUtils;
import kr.co.kesti.utils.ResourceUtils;
import kr.co.kesti.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Value("${spring.mail.username}")
    private String emailFromAddress;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;
    @Resource(name = "memberRepository")
    private MemberRepository memberRepository;
    @Resource(name = "memberAuthRepository")
    private MemberAuthRepository memberAuthRepository;

    @Override
    public MemberAuth findByUsername(final String username) {
        return this.memberAuthRepository.findByUsername(username);
    }

    @Override
    public boolean isExistUser(final String username) {
        return this.memberRepository.findByUsername(username) != null;
    }

    @Transactional
    @Override
    public void signUp(MemberVO memberVO) {
        memberVO.setPassword(this.passwordEncoder.encode(memberVO.getPassword()));
        Member member = memberVO.toMember();
        MemberAuth memberAuth = memberVO.toMemberAuth();
        this.memberRepository.save(member);
        this.memberAuthRepository.save(memberAuth);
    }

    @Override
    public Member findUsername(MemberInfo memberInfo) {
        return this.memberRepository.findByMemberInfo(memberInfo);
    }

    @Transactional
    @Override
    public void resetPassword(final String username, final String email) {
        final String newPassword = RandomStringUtils.randomAlphanumeric(10);

        try {
            String emailContents = ResourceUtils.readText("/templates/findPasswordHTML.html");
            emailContents = emailContents.replace("${newPassword}", newPassword);
            MailUtils.sendEmail(
                    this.emailFromAddress,
                    email,
                    MessageUtils.getMessage("mail.findPassword.title"),
                    emailContents,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MemberAuth memberAuth = this.memberAuthRepository.findByUsername(username);
        memberAuth.setPassword(this.passwordEncoder.encode(CryptoUtils.encryptSHA256(newPassword)));
        this.memberAuthRepository.save(memberAuth);
    }
}