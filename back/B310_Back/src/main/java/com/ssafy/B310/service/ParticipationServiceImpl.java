package com.ssafy.B310.service;

import com.ssafy.B310.entity.Participation;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.ParticipationRepository;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    ParticipationRepository participationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

//    @Override
//    public int joinRoom(User user, int roomNum) throws SQLException {
//        // 해당 방에 유저가 존재 할 경우 처리??
//        Optional<User> joinUser = userRepository.findByUserId(user.getUserId());
//        Optional<Room> joinRoom = roomRepository.findByRoomName(room.getRoomName());
//
//        User u = joinUser.get();
//
//        u.setUserId(user.getUserId());
//
//        Room r = joinRoom.get();
//
//        r.setRoomNum(room.getRoomNum());
//
//        Participation participation = new Participation(r, u);
//        return 0;
//    }

    @Override
    public int joinRoom(User user, int roomNum) throws SQLException {
        // 해당 방에 유저가 존재 할 경우 처리??
        Optional<User> joinUser = userRepository.findByUserId(user.getUserId());
        Optional<Room> joinRoom = roomRepository.findById(roomNum);

        User u = joinUser.get();

        u.setUserId(user.getUserId());

        Room r = joinRoom.get();

        r.setRoomNum(roomNum);

        Participation participation = new Participation(r, u);
        participation.setUser(u);
        participation.setRoom(r);
        participationRepository.save(participation);

        System.out.println(u);
        System.out.println(r);
        return 1;
    }

}
