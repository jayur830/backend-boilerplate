package kr.co.kesti.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> toObject() {
        Map<String, Object> object = new HashMap<>();

        object.put("username", this.username);
        object.put("password", this.password);
        object.put("authority", this.authority);
        object.put("accountExpired", this.accountExpired);
        object.put("accountLocked", this.accountLocked);
        object.put("credentialsExpired", this.credentialsExpired);
        object.put("enabled", this.enabled);

        return object;
    }
}