package com.ssafy.B310.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.ParticipationHistory;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.ParticipationHistoryQueryRepository;
import com.ssafy.B310.repository.ParticipationHistoryRepository;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.repository.UserRepository;

@Service
public class ParticipationHistoryServiceImpl implements ParticipationHistoryService {
	
	@Autowired
	ParticipationHistoryRepository participationHistoryRepository;
	@Autowired
	ParticipationHistoryQueryRepository participationHistoryQueryRepository;
	
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;
	
	// 방문했던 방 목록에 추가
	@Override
	public int createRoomHistory(User user, Room room) throws SQLException {
		try {
			List<ParticipationHistory> participationHistoryList = participationHistoryQueryRepository.findParticipationHistory(user.getUserId(), room.getRoomNum());
			ParticipationHistory ph;
			if (participationHistoryList.size() > 0) {
				ph = participationHistoryList.get(0);
				ph.setParticipationHistoryUpdateTime(LocalDateTime.now());
			} else {
				ph = new ParticipationHistory(room, user, LocalDateTime.now());
			}
			participationHistoryRepository.save(ph);
		} catch(Exception e) {
			System.out.println("히스토리 등록 에러");
			return 0;
		}
		return 1;
	}

	// 유저별 방문했던 방 조회
	@Override
	public List<Room> getRoomHistoryList(String userId) throws SQLException {
		return null;
	}

}
