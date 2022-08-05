package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.entity.UserHashtag;
import com.ssafy.B310.repository.UserHashtagRepository;

@Service
public class UserHashtagServiceImpl implements UserHashtagService {
	
	@Autowired
	UserHashtagRepository uhr;

	@Override
	public int addUserHashtag(UserHashtag hashtag) throws SQLException {
		UserHashtag userHashtag = uhr.save(hashtag);
		if(userHashtag != null)
			return 1;
		else return 0;
	}

	@Override
	public int deleteUserHashtag(User user, Hashtag hashtag) throws SQLException {
		return uhr.deleteByUserAndHashtag(user, hashtag);
	}

	@Override
	public List<UserHashtag> getUserHashtag(User user) throws SQLException {
		return uhr.findByUser(user);
	}

}
