package com.ssafy.B310.service;

import com.ssafy.B310.entity.Follow;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.FollowRepository;
import com.ssafy.B310.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {
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
        f.setFollowFromUser(u1);
        f.setFollowToUser(u2);

        followRepository.save(f);
    }

    public void deleteFollowFromUserAndFollowToTuser() {

    }

    @Override
    public int judgeByFollowerAndFollowingUserId(String userId) {
        return 0;
    }

//    int judgeByFollowerAndFollowingUserId(String userId){
//        Follow f = new Follow();
//
//
//    }
}
