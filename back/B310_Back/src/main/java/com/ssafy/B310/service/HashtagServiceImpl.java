package com.ssafy.B310.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.repository.HashtagRepository;
import com.ssafy.B310.repository.RoomRepository;

@Service
public class HashtagServiceImpl implements HashtagService {
	
	@Autowired
	HashtagRepository hashRepo;
	
	public int createHashtag(Hashtag hashtag) {
		Optional<Hashtag> oHashtag = hashRepo.findById(hashtag.getHashtagNum());
		
		int result = 0;
		
		if(!oHashtag.isPresent()) {
			hashRepo.save(hashtag);
			result = 1;
		}
		
		return result;
	}
	
	public int updateHashtag(Hashtag hashtag) {
		Optional<Hashtag> oHashtag = hashRepo.findById(hashtag.getHashtagNum());
		
		int result = 0;
		
		if(oHashtag.isPresent()) {
			hashRepo.save(hashtag);
			result = 1;
		}
		
		return result;
	}
	
	public int removeHashtag(int hashtagNum) {
		Optional<Hashtag> oHashtag = hashRepo.findById(hashtagNum);
		
		int result = 0;
		
		if(oHashtag.isPresent()) {
			hashRepo.deleteById(hashtagNum);
			result = 1;
		}
		
		return result;
	}
	
	public List<Hashtag> getHashtagList() {
		return hashRepo.findAll();
	}
}
