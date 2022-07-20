package com.ssafy.B310.service;

import java.sql.SQLException;

import com.ssafy.B310.entity.Room;

public interface RoomService {

	public int registRoom(Room room) throws SQLException;
}
