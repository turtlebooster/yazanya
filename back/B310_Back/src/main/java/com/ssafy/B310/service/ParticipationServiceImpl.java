package com.ssafy.B310.service;

import java.sql.SQLException;
import java.time.Duration;
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
    public int joinRoom(String userId, Room room) throws SQLException {    	

        if (participationRepository.findByuser_userId(userId).isPresent()) {
            return 0;
        }
        
        Optional<User> joinUser = userRepository.findByUserId(userId);

        User u = joinUser.get();

        Participation participation = new Participation(room, u);
        participationRepository.save(participation);
        
        return participationHistoryService.createRoomHistory(u, room);
    }
    
    // 유저 퇴장
    @Override
    public int exitRoom(String userId, int roomNum) throws SQLException {

        Optional<Room> joinRoom = roomRepository.findById(roomNum);

        Room room = joinRoom.get();

        List<Participation> joinedUser = participationRepository.findByRoom(room);
        for (Participation participation : joinedUser)
            if (Objects.equals(participation.getUser().getUserId(), userId)) {
            	// 유저 누적 시간 갱신
            	User user = userRepository.findByUserId(userId).get();
            	Duration addTime = Duration.between(participation.getParticipationEnterTime(), LocalDateTime.now());
            	user.setProfileTotalStudyTime(user.getProfileTotalStudyTime() + (int)(addTime.getSeconds() / 60));    
            	userRepository.save(user);
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

        for (Participation user : participationList) 
            participationListRes.add(user.getUser());
        
        return participationListRes;
    }
}
