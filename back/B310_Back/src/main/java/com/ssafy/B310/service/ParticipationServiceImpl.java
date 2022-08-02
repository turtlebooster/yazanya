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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    ParticipationRepository participationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    // 유저 입장
    @Override
    public int joinRoom(String userId, Room room) throws SQLException {    	
        if (participationRepository.findByuser_userId(userId).isPresent()) {
            return 0;
        }
        
        Optional<User> joinUser = userRepository.findByUserId(userId);

        User u = joinUser.get();

//        u.setUserId(userId);

        Participation participation = new Participation(room, u);
        participation.setUser(u);
        participation.setRoom(room);
        participationRepository.save(participation);

        return 1;


    }
    // 유저 퇴장
    @Override
    public int exitRoom(String userId, int roomNum) throws SQLException {

        Optional<Room> joinRoom = roomRepository.findById(roomNum);

        Room room = joinRoom.get();

        List<Participation> joinedUser = participationRepository.findByRoom(room);
        for (Participation participation : joinedUser)
            if (Objects.equals(participation.getUser().getUserId(), userId)) {
                participationRepository.delete(participation);
                return 1;
            }
        return 0;
    }

    //입장 유저 목록 조회
    @Override
    public List<User> joinedUser(int roomNum) throws SQLException {

        List<Participation> participationList = participationRepository.findByroom_roomNum(roomNum);

        List<User> participationListRes = new ArrayList<>();

        for (Participation user : participationList) {
//            User joinuser = user.getUser();
//            participationListRes.add(joinuser);
            User u = user.getUser();
            participationListRes.add(new User(u.getUserNum(), u.getUserId(), u.getUserNickname()));
//            participationListRes.add(new ParticipationListRes(user.getUser().getUserNickname()));

        }
        return participationListRes;
    }
}
