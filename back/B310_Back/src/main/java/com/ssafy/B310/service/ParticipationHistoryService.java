package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;

public interface ParticipationHistoryService {
	// 방문했던 방 목록에 추가
	public int createRoomHistory(User user, Room room) throws SQLException;
	// 유저별 방문했던 방 조회
	public List<Room> getRoomHistoryList(String userId) throws SQLException;
}
