package kr.co.kesti.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class CustomUserDetails implements UserDetails {
    @Getter
    @NonNull
    private String username;

    @Getter
    @Setter
    @With
    @NonNull
    private String password;

    @Getter
    @Setter
    @With
    private boolean accountExpired = false;

    @Getter
    @Setter
    @With
    private boolean accountLocked = false;

    @Getter
    @Setter
    @With
    private boolean credentialsExpired = false;

    @Getter
    @With
    private String authority;

    @Setter
    @With
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(this.authority));
        return auth;
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