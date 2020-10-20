package kr.co.kesti.repository.member;

import kr.co.kesti.security.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAuthRepository extends JpaRepository<CustomUserDetails, Long> {
    CustomUserDetails findByUsername(final String username);
}