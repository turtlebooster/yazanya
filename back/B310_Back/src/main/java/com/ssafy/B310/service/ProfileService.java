package com.ssafy.B310.service;

import com.ssafy.B310.entity.Profile;
import com.ssafy.B310.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public interface ProfileService {
    int createProfile() throws SQLException;

    int updateProfile(Profile profile) throws SQLException;

    int getProfile(String userId) throws SQLException;
}
