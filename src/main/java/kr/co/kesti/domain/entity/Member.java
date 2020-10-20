package kr.co.kesti.domain.entity;

import kr.co.kesti.domain.embed.MemberInfo;
import kr.co.kesti.security.CustomUserDetails;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "memberAuth")
public class Member {
    @Id
    @Column(name = "username", length = 200)
    @Setter
    private String username;

    @Setter
    private MemberInfo memberInfo;

    @OneToOne
    @JoinColumn(name = "username")
    private CustomUserDetails memberAuth;
}