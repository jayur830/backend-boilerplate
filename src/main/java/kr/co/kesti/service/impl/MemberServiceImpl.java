package kr.co.kesti.service.impl;

import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.service.MemberService;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Override
    public void addMember(Member member) {

    }

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
