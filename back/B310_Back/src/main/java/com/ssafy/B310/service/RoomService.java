package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.B310.entity.Room;

public interface RoomService {
	// 规 积己
	public int createRoom(Room room) throws SQLException;
	// 规 炼雀
	public List<Room> filterRoom(Map<String, Integer> params) throws SQLException;
	// 规 沥焊 荐沥
	public int updateRoom(Room room) throws SQLException;
	// 规 昏力
	public int removeRoom(int roomNum) throws SQLException;
	
}
