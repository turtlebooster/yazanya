package com.ssafy.B310.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.RoomRepository;

public class RoomServiceImpl {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public int registRoom(Room room) throws SQLException {
		
		// 이미 해당 아이디나 이메일로 가입된 유저가 있을경우
		if (roomRepository.findByRoomName(room.getRoomName()).isPresent()) {
			return 0;
		}
		
		// 없을경우
		roomRepository.save(room);
		return 1;
	}
}
