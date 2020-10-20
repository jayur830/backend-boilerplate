package kr.co.kesti.repository.member;

import kr.co.kesti.domain.embed.MemberInfo;
import kr.co.kesti.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    long countByUsername(final String username);
    Member findByUsername(final String username);

    @Query("select m from Member m where m.memberInfo = :memberInfo")
    Member findByMemberInfo(MemberInfo memberInfo);
}