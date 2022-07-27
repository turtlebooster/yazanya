package com.ssafy.B310.service;

import java.sql.SQLException;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;
import java.util.List;

public interface ParticipationService {

//    public int joinRoom(User user, Room room) throws SQLException;

    public int joinRoom(User user, Room room) throws SQLException;

    public int exitRoom(int userNum, int roomNum) throws SQLException;


    public List<User> joinedUser(int roomNum) throws SQLException;
}
