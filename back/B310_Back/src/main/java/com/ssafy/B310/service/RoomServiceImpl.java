package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.repository.RoomQueryRepository;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.specification.RoomSpecification;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	RoomSpecification roomSpecification;
	
	@Autowired
	RoomQueryRepository roomQueryRepository;
	
	@Override
	public int createRoom(Room room) throws SQLException {
		Room newRoom = roomRepository.save(room);
		return newRoom.getRoomNum();
	}

	@Override
	public int updateRoom(Room room) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(room.getRoomNum()); 
		
		if (oRoom.isPresent()) {
			Room r = oRoom.get();
			r.setRoomName(room.getRoomName());
			r.setRoomStudyTime(room.getRoomStudyTime());
			r.setRoomRestTime(room.getRoomRestTime());
			r.setRoomVideo(room.isRoomVideo());
			r.setRoomSound(room.isRoomSound());
			r.setRoomPw(room.getRoomPw());
			r.setRoomThumbnail(room.getRoomThumbnail());
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

	@Override
	public Room getRoom(int roomNum) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(roomNum); 
		// 해당 방이 있을경우
		if (oRoom.isPresent()) {
			Room r = oRoom.get();
			return r;
		}
		else return null;
	}

	// 해쉬태그 추천 목록
	public List<Room> getRecommendHashtagList(List<Integer> hashtagNumList) {
		return roomQueryRepository.findRecommendRoom(hashtagNumList);

	}
	
	//유저 입장할 때 participation 1 증가
	@Override
	public void addParticipation(Room room) throws SQLException {
		roomQueryRepository.increaseParticipationCount(room);
	}

	//유저 퇴장할 때 participation 1 감소
	@Override
	public void decreaseParticipation(Room room) throws SQLException {
		roomQueryRepository.decreaseParticipationCount(room);
		
	}

	@Override
	public boolean enableJoinRoom(int roomNum) throws SQLException {
		Room room = roomRepository.findById(roomNum).get();
		
		if(room.isRoomActive() && (room.getRoomCapacity() > room.getRoomParticipationCount()))
			return true;
		else return false;
	}

	@Override
	public long addThumbnail(int roomNum, String filename) throws SQLException {
		return roomQueryRepository.addThumbnailonRoom(roomNum, filename);
	}	
}

