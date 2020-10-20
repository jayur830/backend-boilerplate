package kr.co.kesti.service;

import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.vo.MemberVO;

public interface MemberService {
    boolean isExistUser(final String username);
    void signUp(MemberVO memberVO);
    Member findUsername(final String nickname, final String email);
    void resetPassword(final String username, final String email);
}