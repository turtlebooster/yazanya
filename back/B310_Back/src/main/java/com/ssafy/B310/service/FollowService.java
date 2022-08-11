package com.ssafy.B310.service;

import com.ssafy.B310.entity.Follow;
import com.ssafy.B310.entity.User;

import java.util.List;

public interface FollowService {

    public Follow follow(String followToUserId, String followFromUserId);

    public void deleteByFollowToUserAndFollowFromUser(String followToUserId, String followFromUserId);

    public List<User> followList(String userId);

    public List<User> followerList(String userId);
}
