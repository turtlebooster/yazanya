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
import java.util.Optional;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    ParticipationRepository participationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public int joinRoom(User user, int roomNum) throws SQLException {
        Optional<User> joinUser = userRepository.findByUserId(user.getUserId());

        if (participationRepository.findByuser_userId(user.getUserId()).isPresent()){
            return 2;
        }

        User u = joinUser.get();

        u.setUserId(user.getUserId());

        Participation participation = new Participation(room, u);
        participation.setUser(u);
        participation.setRoom(room);
        participationRepository.save(participation);

        return 1;


    }

    @Override
    public int exitRoom(int userNum, int roomNum) throws SQLException {

        Optional<Room> joinRoom = roomRepository.findById(roomNum);

        Room room = joinRoom.get();

        List<Participation> joinedUser = participationRepository.findByRoom(room);
        for (Participation participation : joinedUser)
            if (participation.getUser().getUserNum() == userNum) {
                participationRepository.delete(participation);
            }
        return 1;
    }

    @Override
    public List<User> joinedUser(int roomNum) throws SQLException {

        List<Participation> participationList = participationRepository.findByroom_roomNum(roomNum);

        List<User> participationListRes = new ArrayList<>();

        for (Participation user : participationList) {
            User joinuser = user.getUser();
            participationListRes.add(joinuser);
        }
        return participationListRes;
    }

}
