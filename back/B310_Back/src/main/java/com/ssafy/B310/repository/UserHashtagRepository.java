package com.ssafy.B310.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.entity.UserHashtag;

@Repository
public interface UserHashtagRepository extends JpaRepository<UserHashtag, String> {
	List<UserHashtag> findByUser(User user);
	
	@Transactional
	int deleteByUserAndHashtag(User user, Hashtag hashtag);
	int countByUserAndHashtag(User user, Hashtag hashtag);
}
