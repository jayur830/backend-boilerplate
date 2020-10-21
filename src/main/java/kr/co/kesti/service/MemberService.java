package kr.co.kesti.service;

import kr.co.kesti.domain.embed.MemberInfo;
import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.domain.entity.MemberAuth;
import kr.co.kesti.vo.MemberVO;

public interface MemberService {
    MemberAuth findByUsername(final String username);
    boolean isExistUser(final String username);
    void signUp(MemberVO memberVO);
    Member findUsername(MemberInfo memberInfo);
    void resetPassword(final String username, final String email);
}