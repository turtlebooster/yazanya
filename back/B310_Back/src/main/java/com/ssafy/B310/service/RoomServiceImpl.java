package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	
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
		
		switch (video) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}
		
		switch (sound) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}
		
		switch (fullcheck) {
		case 1:
			break;
		case 2:
			break;
		}
		
		return roomRepository.findAll();
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



	


}
