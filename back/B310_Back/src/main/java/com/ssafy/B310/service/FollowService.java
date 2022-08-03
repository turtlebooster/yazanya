package com.ssafy.B310.service;

import com.ssafy.B310.entity.Follow;
import com.ssafy.B310.entity.User;

public interface FollowService {

    public Follow follow(String followToUserId, String followFromUserId);

    public void deleteByFollowToUserAndFollowFromUser(String followToUserId, String followFromUserId);


}
