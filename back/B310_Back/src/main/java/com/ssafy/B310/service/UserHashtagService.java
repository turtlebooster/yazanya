package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.entity.UserHashtag;

public interface UserHashtagService {
	//유저 해쉬태그 추가
	public int addUserHashtag(UserHashtag userHashtag) throws SQLException;
	//유저 해쉬태그 삭제
	public int deleteUserHashtag(User user, Hashtag hashtag) throws SQLException;
	//유저 해쉬태그 목록
	public List<UserHashtag> getUserHashtag(User user) throws SQLException;
}
