package kr.co.kesti.repository;

import kr.co.kesti.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<UserDetails, Long> {
    @Query("select u from UserAuth u where u.username = :username")
    Member findMember(final String username);
}
