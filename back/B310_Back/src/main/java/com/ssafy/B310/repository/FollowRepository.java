package com.ssafy.B310.repository;

import com.ssafy.B310.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    int judgeByFollowerAndFollowingUserId(String userId);


    void deleteByFollowingIdAndFollowerId(String userId1, String userId2);
}
