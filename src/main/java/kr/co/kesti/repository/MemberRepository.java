package kr.co.kesti.repository;

import kr.co.kesti.domain.embed.MemberInfo;
import kr.co.kesti.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByUsername(final String username);
    void removeMemberByUsername(final String username);
    Member findMemberByMemberInfo(final MemberInfo memberInfo);
}