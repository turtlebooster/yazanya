package com.ssafy.B310.service;

import com.ssafy.B310.entity.Follow;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.FollowRepository;
import com.ssafy.B310.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowServiceImpl {
    @Autowired
    FollowService followService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

    public void follow(String from_userId, String to_userId){
        Optional<User> o1 = userRepository.findByUserId(from_userId);
        Optional<User> o2 = userRepository.findByUserId(to_userId);

        User u1 = o1.get();

        User u2 = o2.get();

        Follow f = new Follow();

        u1.setUserId(from_userId);
        u2.setUserId(to_userId);
        f.setFrom_user(u1);
        f.setTo_user(u2);

        followRepository.save(f);
    }

    int judgeByFollowerAndFollowingUserId(String userId){
        Follow f = new Follow();


    }
}
