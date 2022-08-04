package com.ssafy.B310.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserEmail(String userEmail);
    void deleteByUserId(String userId);

    Optional<User> findByUserNum(int userNum);
    
    Optional<User> findByUserNickname(String userNickname);
}