package com.ssafy.B310.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.B310.entity.Participation;
import com.ssafy.B310.entity.ParticipationHistory;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.ParticipationHistoryQueryRepository;
import com.ssafy.B310.repository.ParticipationHistoryRepository;
import com.ssafy.B310.repository.ParticipationRepository;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.repository.UserRepository;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    ParticipationRepository participationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ParticipationHistoryService participationHistoryService;
    
    // 유저 입장
    @Transactional
    @Override
    public int joinRoom(User user, Room room) throws SQLException {
        Optional<User> joinUser = userRepository.findByUserId(user.getUserId());

        if (participationRepository.findByuser_userId(user.getUserId()).isPresent()) {
            return 2;
        }

        User u = joinUser.get();

        u.setUserId(user.getUserId());
        u.setRoom(room);

        Participation participation = new Participation(room, u);
//        participation.setUser(u);
//        participation.setRoom(room);
        participationRepository.save(participation);
        
        // history에 기록
        // 잘 기록됐으면 1 아니면 0 반환
        return participationHistoryService.createRoomHistory(u, room);
        
//        return 1;


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
