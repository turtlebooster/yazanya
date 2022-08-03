package com.ssafy.B310.repository;

import com.ssafy.B310.entity.Follow;
import com.ssafy.B310.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    @Modifying
    @Transactional
    void deleteByFollowToUserAndFollowFromUser(User followToUser, User followFromUser);
}
