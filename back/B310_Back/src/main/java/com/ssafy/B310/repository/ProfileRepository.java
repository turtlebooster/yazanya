package com.ssafy.B310.repository;

import com.ssafy.B310.entity.Profile;
import com.ssafy.B310.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<User, Integer> {
//    public List<Profile> findByUserId(String uesrId);
}
