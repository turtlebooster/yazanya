package com.ssafy.B310.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.ParticipationHistory;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	RoomHashtagRepository roomHashtagRepository;

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
		} catch (Exception e) {
			System.out.println("히스토리 등록 에러");
			return 0;
		}
		return 1;
	}

	// 유저별 방문했던 방 조회
	@Transactional
	@Override
	public List<Map<String, Object>> getRoomHistoryList(String userId) throws SQLException {
//		Map<String, Object> result = new HashMap<>();
//		Map<String, Object> roomData = new HashMap<>();
		List<String> tempRoomHsNameList;
		List<Map<String, Object>> realresult = new ArrayList<>();


		Map<String, Object> temp;

		List<Room> userHistory = participationHistoryQueryRepository.findParticipationHistoryByUserId(userId);
		System.out.println(userHistory.size());
//		for (int i = 0; i < userHistory.size(); i++) {
			for (Room roomH : userHistory) {
				temp = new HashMap<>();
				temp.put("userHistory", roomH);
				List<RoomHashtag> roomHs = roomHashtagRepository.findByRoom(roomH);

					tempRoomHsNameList = new ArrayList<>();
				for (RoomHashtag roomHa : roomHs) {
//					System.out.println(roomH);
					String roomHsName = roomHa.getHashtag().getHashtagName();
					tempRoomHsNameList.add(roomHsName);
				}
					temp.put("roomHash", tempRoomHsNameList);
			realresult.add(temp);
			}

//
//		}
//		for (Room roomH:userHistory) {
//			List<Object> resultList = new ArrayList<>();
//		}
//		List<String> roomHsNameList = new ArrayList<>();
//
//		roomData.put("userHistory", userHistory);
//		for (Room r : userHistory) {
//			List<RoomHashtag> roomHs = roomHashtagRepository.findByRoom(r);
//
//			for (RoomHashtag roomH : roomHs) {
//				System.out.println(roomH);
//				String roomHsName = roomH.getHashtag().getHashtagName();
//				roomHsNameList.add(roomHsName);
//				roomData.put("roomHash", roomHsNameList);
//			}
//
//		}
		return realresult;
	}
}

