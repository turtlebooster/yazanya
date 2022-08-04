package com.ssafy.B310.service;

import com.ssafy.B310.entity.Profile;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public interface ProfileService {

    int updateProfile(String userId, User user) throws SQLException;

    User getProfile(String userId) throws SQLException;

    int updateProfileImg(String userId, String imgPath) throws SQLException;
}
