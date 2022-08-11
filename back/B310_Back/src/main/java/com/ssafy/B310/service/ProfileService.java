package com.ssafy.B310.service;

import com.ssafy.B310.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public interface ProfileService {

    int updateProfile(String userId, User user) throws SQLException;

    User getProfile(String userId) throws SQLException;

    int uploadProfileImg(String userId, String imgPath) throws SQLException;

	int updatePlannerSet(String userId, String profilePlannerSet) throws SQLException;
    }
