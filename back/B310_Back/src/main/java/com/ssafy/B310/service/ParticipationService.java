package com.ssafy.B310.service;

import com.ssafy.B310.entity.Participation;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;

import java.sql.SQLException;

public interface ParticipationService {

//    public int joinRoom(User user, Room room) throws SQLException;

    public int joinRoom(User user,int roomNum) throws SQLException;



}
