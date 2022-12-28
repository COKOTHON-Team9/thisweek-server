package com.susu.defaultserver.api.repository;

import com.susu.defaultserver.api.entity.Member;
import com.susu.defaultserver.api.entity.Heart;
import com.susu.defaultserver.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findHeartByMemberIdAndPostId(Long memberId, Long postId);

}
