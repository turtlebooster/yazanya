package com.ssafy.B310.service;

import java.sql.SQLException;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;
import java.util.List;

public interface ParticipationService {

    public int joinRoom(String userId, Room room) throws SQLException;

    public int exitRoom(String userId, int roomNum) throws SQLException;


    public List<User> joinedUser(int roomNum) throws SQLException;
}
