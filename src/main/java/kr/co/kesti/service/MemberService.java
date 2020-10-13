package kr.co.kesti.service;

import kr.co.kesti.domain.entity.Member;
import org.springframework.stereotype.Service;

public interface MemberService {
    void addMember(Member member);
    void removeMember(final String username);
    String findUsername(Member member);
    void findPassword(Member member);
}