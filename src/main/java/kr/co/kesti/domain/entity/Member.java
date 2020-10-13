package kr.co.kesti.domain.entity;

import kr.co.kesti.domain.embed.MemberAuth;
import kr.co.kesti.domain.embed.MemberInfo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private MemberInfo memberInfo;
    private MemberAuth memberAuth;
}