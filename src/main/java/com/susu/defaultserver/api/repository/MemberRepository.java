package com.susu.defaultserver.api.repository;

import com.susu.defaultserver.api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /* 중복 검사 */
    boolean existsByNickname(String nickname);

    boolean existsByEmailAndPassword(String email, String password);

    Member findMemberByEmail(String email);
}
