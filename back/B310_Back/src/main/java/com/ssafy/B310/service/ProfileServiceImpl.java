package com.ssafy.B310.service;

import com.ssafy.B310.entity.Profile;
import com.ssafy.B310.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public int createProfile() {
        profileRepository.save();
        return 0;
    }

    @Override
    public int updateProfile(Profile profile) {

        profileRepository.save(profile);
        return 0;
    }

    @Override
    public int getProfile(String userId) {
        return 0;
    }
}
