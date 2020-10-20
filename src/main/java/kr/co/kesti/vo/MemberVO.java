package kr.co.kesti.vo;

import kr.co.kesti.define.Role;
import kr.co.kesti.domain.embed.MemberInfo;
import kr.co.kesti.domain.entity.Member;
import kr.co.kesti.security.CustomUserDetails;
import lombok.*;

@Getter
@Setter
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phoneNum;

    public Member toMember() {
        return Member.builder()
                .username(this.username)
                .memberInfo(MemberInfo.builder()
                        /**
                         * 회원 상세 정보
                         * */
                        .build())
                .build();
    }

    public CustomUserDetails toMemberAuth() {
        return CustomUserDetails.builder()
                .username(this.username)
                .password(this.password)
                .authority(Role.MEMBER)
                .build();
    }
}