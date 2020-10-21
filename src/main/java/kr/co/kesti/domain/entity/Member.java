package kr.co.kesti.domain.entity;

import kr.co.kesti.domain.embed.MemberInfo;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "memberAuth")
public class Member {
    @Id
    @Column(length = 200)
    @Setter
    private String username;

    @Setter
    private MemberInfo memberInfo;

    @OneToOne
    @JoinColumn(name = "username")
    private MemberAuth memberAuth;

    public Map<String, Object> toObject() {
        Map<String, Object> object = new HashMap<>();

        object.put("username", this.username);
        object.put("name", this.memberInfo.getName());
        object.put("email", this.memberInfo.getEmail());
        object.put("phoneNum", this.memberInfo.getPhoneNum());

        return object;
    }
}