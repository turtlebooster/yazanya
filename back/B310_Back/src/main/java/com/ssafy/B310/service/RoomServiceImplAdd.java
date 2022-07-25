package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.repository.HashtagRepository;
import com.ssafy.B310.repository.RoomHashtagRepository;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.specification.RoomSpecification;

@Service
public class RoomServiceImplAdd implements RoomService {

	@Autowired
	RoomRepository roomRepository;
	RoomSpecification roomSpecification;
	@Autowired
	RoomHashtagRepository roomHashtagRepository;
	@Autowired
	HashtagRepository hashtagRepository;
	
	
	@Override
	public int createRoom(Room room) throws SQLException {
		// JPA save로 반환되는 객체가 이번에 생성된 room 인지?
		Room newRoom = roomRepository.save(room);
		return newRoom.getRoomNum();
	}



	@Override
	public int updateRoom(Room room) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(room.getRoomNum()); 
		// 해당 방이 있을경우
		if (oRoom.isPresent()) {
			Room r = oRoom.get();
			// 해시태그는 여기서 업데이트 못함
			// 방 제목과 알람설정 수정
			r.setRoomName(room.getRoomName());
			r.setRoomStudyTime(room.getRoomStudyTime());
			r.setRoomRestTime(room.getRoomRestTime());
			roomRepository.save(r);
			return 1;
		}
		// 없을경우
		return 0;
	}



	@Override
	public List<Room> filterRoom(Map<String, Integer> params) throws SQLException {
		int video = params.get("video");
		int sound = params.get("sound");
		int fullcheck = params.get("fullcheck");
		
		Specification<Room> spec = (root, query, ct) -> null;
		
		spec = spec.and(roomSpecification.videoSetting(video));
		spec = spec.and(roomSpecification.soundeSetting(sound));
		spec = spec.and(roomSpecification.isFull(fullcheck));
		return roomRepository.findAll(spec);
	}



	@Override
	public int removeRoom(int roomNum) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(roomNum);
		// 해당 방이 있을경우
		if (oRoom.isPresent()) {
			Room r = oRoom.get();
			roomRepository.deleteById(roomNum);
			return 1;
		}
		// 없을경우
		return 0;
	}
	
	// 해쉬태그 추천 목록
	public List<Room> getRecommendHashtagList(List<Integer> hashtagNumList) {
		
//		List<Room> list = roomHashtagRepository.findByHashtagHashtagNumIn(hashtagNumList);
//		Set<Room> roomSet = new HashSet<Room>();
//		roomSet.add(list.get(0).getRoom());
//		System.out.println(roomSet.toString());
		
		/*
		List<Hashtag> hashtagList = new ArrayList<Hashtag>();
		for (int hashtagNum : hashtagNumList) {
			hashtagList.add(hashtagRepository.findById(hashtagNum).get());
//			System.out.println(hashtagNum);
		}
		
//		System.out.println(hashtagList.get(0).getHashtagName());
		
//		System.out.println(hashtagList.toString());
		List<RoomHashtag> roomNumList = roomHashtagRepository.findByHashtagIn(hashtagList);
		List<Room> roomList = new ArrayList<Room>();
		for (RoomHashtag rh : roomNumList) {
//			System.out.println(rh.getId());
			roomList.add(rh.getRoom());
		}
		*/
//		List<Room> roomList = new ArrayList<Room>();
		return roomRepository.findByRoomByHashtagNum(hashtagNumList);
	}

}
