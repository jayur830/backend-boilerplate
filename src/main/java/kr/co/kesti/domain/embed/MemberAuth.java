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
public class MemberAuth {
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
}