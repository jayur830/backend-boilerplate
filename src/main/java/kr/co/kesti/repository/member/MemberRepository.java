package kr.co.kesti.repository.member;

import kr.co.kesti.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    long countByUsername(final String username);
    Member findByUsername(final String username);
    Member findByNicknameAndEmail(final String nickname, final String email);
}