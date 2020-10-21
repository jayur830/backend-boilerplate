package kr.co.kesti.repository.member;

import kr.co.kesti.domain.entity.MemberAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAuthRepository extends JpaRepository<MemberAuth, String> {
    MemberAuth findByUsername(final String username);
}