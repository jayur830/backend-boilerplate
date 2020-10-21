package kr.co.kesti.domain.embed;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberInfo {
    /**
     * 회원의 아이디, 패스워드 외에
     * 상세 정보로 들어갈 컬럼들을 추가하면 됩니다.
     * */
    private String name;
    private String email;
    private String phoneNum;
}