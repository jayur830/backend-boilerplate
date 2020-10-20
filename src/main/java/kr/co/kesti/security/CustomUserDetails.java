package kr.co.kesti.security;

import lombok.*;
import kr.co.kesti.domain.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "MemberAuth")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "member")
public class CustomUserDetails implements UserDetails {
    @Id
    @Column(name = "username", length = 200)
    @Setter
    private String username;

    @Setter
    private String password;

    @Setter
    private String authority;

    @Setter
    private boolean accountExpired = false;

    @Setter
    private boolean accountLocked = false;

    @Setter
    private boolean credentialsExpired = false;

    @Setter
    private boolean enabled = true;

    @OneToOne(mappedBy = "memberAuth")
    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.authority));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}