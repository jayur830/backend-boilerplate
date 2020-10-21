package kr.co.kesti.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "member")
public class MemberAuth {
    @Id
    @Column(length = 200)
    private String username;
    private String password;
    private String authority;

    @Builder.Default
    private boolean accountExpired = false;
    @Builder.Default
    private boolean accountLocked = false;
    @Builder.Default
    private boolean credentialsExpired = false;
    @Builder.Default
    private boolean enabled = true;

    @OneToOne(mappedBy = "memberAuth")
    private Member member;
}