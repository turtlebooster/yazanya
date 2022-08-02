package com.ssafy.B310.service;

import com.ssafy.B310.entity.Profile;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.ProfileRepository;
import com.ssafy.B310.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public int updateProfile(String userId, User user) {
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User u = oUser.get();
            u.setProfileSelfIntroduce(user.getProfileSelfIntroduce());
            System.out.println(u);
            System.out.println("is?");
//            u.setUserNickname(user.getUserNickname());
            userRepository.save(u);
            return 1;
        }
        System.out.println("else?");
        return 0;
    }
    @Override
    public User getProfile(String userId) {
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User u = oUser.get();
            return u;
        }
        return null;
    }
}
