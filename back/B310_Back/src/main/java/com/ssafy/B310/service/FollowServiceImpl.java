package com.ssafy.B310.service;

import com.ssafy.B310.entity.Follow;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.FollowRepository;
import com.ssafy.B310.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowRepository followRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Follow follow(String followToUserId, String followFromUserId) {
        Optional<User> oUser1 = userRepository.findByUserId(followToUserId);
//        if (oUser1.isPresent()) {
            User followToUser = oUser1.get();
//        }
        Optional<User> oUser2 = userRepository.findByUserId(followFromUserId);
//        if (oUser1.isPresent()) {
        User followFromUser = oUser2.get();
        Follow f = new Follow();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        f.setFollowToUser(followToUser);
        f.setFollowFromUser(followFromUser);
        f.setFollowDate(timestamp);

        followRepository.save(f);

        return f;
    }

    @Override
    public void deleteByFollowToUserAndFollowFromUser(String followToUserId, String followFromUserId) {
        Optional<User> oUser1 = userRepository.findByUserId(followToUserId);
//        if (oUser1.isPresent()) {
        User followToUser = oUser1.get();
//        }
        Optional<User> oUser2 = userRepository.findByUserId(followFromUserId);
//        if (oUser1.isPresent()) {
        User followFromUser = oUser2.get();
        followRepository.deleteByFollowToUserAndFollowFromUser(followToUser, followFromUser);
    }
}
