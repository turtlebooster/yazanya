package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.B310.entity.Room;

public interface RoomService {
	// �� ����
	public int createRoom(Room room) throws SQLException;
	// �� ��ȸ
	public List<Room> filterRoom(Map<String, Integer> params) throws SQLException;
	// �� ���� ����
	public int updateRoom(Room room) throws SQLException;
	// �� ����
	public int removeRoom(int roomNum) throws SQLException;
	
}
