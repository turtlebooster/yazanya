package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.B310.entity.Room;

public interface RoomService {
	// 방 생성
	public int createRoom(Room room) throws SQLException;
	// 방 조회
	public List<Room> filterRoom(Map<String, Integer> params) throws SQLException;
	// 방 정보 수정
	public int updateRoom(Room room) throws SQLException;
	// 방 삭제
	public int removeRoom(int roomNum) throws SQLException;
	
}
