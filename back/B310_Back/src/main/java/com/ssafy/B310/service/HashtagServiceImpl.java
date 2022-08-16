package com.ssafy.B310.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.entity.UserHashtag;
import com.ssafy.B310.repository.HashtagRepository;
import com.ssafy.B310.repository.RoomHashtagRepository;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.repository.UserHashtagRepository;
import com.ssafy.B310.repository.UserRepository;

@Service
public class HashtagServiceImpl implements HashtagService {
	
	@Autowired
	HashtagRepository hashRepo;
	
	@Autowired
	RoomRepository roomRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoomHashtagRepository roomHashRepo;
	
	@Autowired
	UserHashtagRepository userHashRepo;
	
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
	
	public Hashtag getHashtag(int hashtagNum) {
		return hashRepo.findById(hashtagNum).get();
	}

	@Override
	public int addRoomHashtagList(List<String> hashtagNameList, int roomNum) {
		Optional<Room> oRoom = roomRepo.findById(roomNum);
		if (oRoom.isPresent()) {
			Room room = oRoom.get();
			List<Hashtag> findList;
			RoomHashtag roomHashtag;
			for (String hashtagName : hashtagNameList) {
				// 비어있는 경우 제외
				if (hashtagName == null || hashtagName.equals("")) continue;
				findList = hashRepo.findByHashtagName(hashtagName);
				// 이미 생성된 해쉬태그인지 체크
				if (findList.size() > 0) {
					roomHashtag = new RoomHashtag(room, findList.get(0));
					// 이미 등록된 해쉬태그인지 체크
					if (roomHashRepo.countByRoomAndHashtag(room, findList.get(0)) > 0) continue;
				} else {
					Hashtag ht = hashRepo.save(new Hashtag(hashtagName));
					roomHashtag = new RoomHashtag(room, ht);
				}
				roomHashRepo.save(roomHashtag);				
			}
			return 1;
		}
		return 0;
	}
	
	@Override
	public int addUserHashtagList(List<String> hashtagNameList, String userId) {
		Optional<User> oUser = userRepo.findByUserId(userId);
		if (oUser.isPresent()) {
			User user = oUser.get();
			// 이전에 등록된 해쉬태그 삭제
			userHashRepo.deleteByUser(user);
			
			// 해쉬태그 리스트 등록
			List<Hashtag> findList;
			UserHashtag userHashtag;
			for (String hashtagName : hashtagNameList) {
				// 비어있는 경우 제외
				if (hashtagName == null || hashtagName.equals("")) continue;
				findList = hashRepo.findByHashtagName(hashtagName);
				// 이미 생성된 해쉬태그인지 체크
				if (findList.size() > 0) {
					userHashtag = new UserHashtag(user, findList.get(0));
					// 이미 등록된 해쉬태그인지 체크
					if (userHashRepo.countByUserAndHashtag(user, findList.get(0)) > 0) continue;
				} else {
					Hashtag ht = hashRepo.save(new Hashtag(hashtagName));
					userHashtag = new UserHashtag(user, ht);
				}
				userHashRepo.save(userHashtag);				
			}
			return 1;
		}
		return 0;
	}

}
