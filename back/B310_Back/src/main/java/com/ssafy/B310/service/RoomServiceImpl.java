package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.*;

import com.ssafy.B310.entity.*;
import com.ssafy.B310.repository.*;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.B310.specification.RoomSpecification;

import javax.transaction.Transactional;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepository;
	RoomSpecification roomSpecification;

	@Autowired
	RoomQueryRepository roomQueryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoomHashtagRepository roomHashtagRepository;

	@Autowired
	RoomForceExitRepository roomForceExitRepository;

	@Autowired
	ParticipationService participationService;

	@Transactional
	@Override
	public int createRoom(Room room, int userNum) throws SQLException {
		Optional<User> oUser = userRepository.findByUserNum(userNum);

		if (oUser.isPresent()) {
			User u = oUser.get();
			room.setUserNum(userNum);
			room.setRoomPw(hashPw(room.getRoomPw()));
			Room newRoom = roomRepository.save(room);
			u.setUserRoomCount(u.getUserRoomCount() + 1);
			userRepository.save(u);
			return newRoom.getRoomNum();
		}
		return 0;
	}

	public String hashPw(String roomPw) {
		return BCrypt.hashpw(roomPw, BCrypt.gensalt());
	}

	@Override
	public int updateRoom(Room room) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(room.getRoomNum());

		if (oRoom.isPresent()) {
			Room r = oRoom.get();
			if (room.getRoomName() != null) r.setRoomName(room.getRoomName());
			if (room.getRoomDescription() != null) r.setRoomDescription(room.getRoomDescription());
			if (room.getRoomCapacity() != 0) r.setRoomCapacity(room.getRoomCapacity());
			if (room.getRoomStudyTime() != 0) r.setRoomStudyTime(room.getRoomStudyTime());
			if (room.getRoomRestTime() != 0) r.setRoomRestTime(room.getRoomRestTime());
			r.setRoomVideo(room.isRoomVideo());
			r.setRoomSound(room.isRoomSound());
			r.setRoomHasPw(room.isRoomHasPw());
			r.setRoomPw(hashPw(room.getRoomPw()));
//			r.setRoomThumbnail(room.getRoomThumbnail());
			r.setRoomActive(room.isRoomActive());

			roomRepository.save(r);
			return 1;
		}
		// 없을경우
		return 0;
	}

	@Override
	public List<Room> filterRoom(Map<String, Integer> params) throws SQLException {
		int video = params.get("video");
		int sound = params.get("sound");
		int fullcheck = params.get("fullcheck");

		Specification<Room> spec = (root, query, ct) -> null;

		spec = spec.and(roomSpecification.videoSetting(video));
		spec = spec.and(roomSpecification.soundeSetting(sound));
		spec = spec.and(roomSpecification.isFull(fullcheck));
		return roomRepository.findAll(spec);
	}

	@Override
	public int removeRoom(int roomNum, int userNum) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(roomNum);
		Optional<User> oUser = userRepository.findByUserNum(userNum);

		// 해당 id의 user가 있으면
		if (oUser.isPresent()) {
			User manager = oUser.get();
			
			// 해당 방이 있을경우
			if (oRoom.isPresent()) {
				Room r = oRoom.get();

				// 삭제 요청한 사람이 해당 방의 관리자가 아닐경우
				if (r.getUserNum() != (userNum)) {
					return 0;
				}

				manager.setUserRoomCount(manager.getUserRoomCount() - 1);
				userRepository.save(manager);
				roomRepository.deleteById(roomNum);
				roomForceExitRepository.deleteByRoom_roomNum(roomNum);
				return 1;
			}
		}
		// 없을경우
		return 0;
	}

	@Override
	public Room getRoom(int roomNum) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(roomNum);
		// 해당 방이 있을경우
		if (oRoom.isPresent()) {
			Room r = oRoom.get();
			return r;
		} else return null;
	}

	// 해쉬태그 추천 목록
	public Map<String, Object> getRecommendHashtagList(List<String > hashtagNameList) {
		List<Room> roomList = roomQueryRepository.findRecommendRoom(hashtagNameList);
		Map<String, Object> result = new HashMap<>();
		List<String> tempRoomHsNameList;
		Map<String, Object> temp;
		List<Map<String, Object>> lst = new ArrayList<>();

//		result.put("roomList", roomList);

		if (roomList.size() == 0) {
			roomList = roomRepository.findAll();
		}
		
		for (Room r : roomList) {
			int roomN = r.getRoomNum();
			temp = new HashMap<>();
			temp.put("room", r);
			List<RoomHashtag> roomHs = roomHashtagRepository.findByRoom(r);

			tempRoomHsNameList = new ArrayList<>();
			for (RoomHashtag roomH : roomHs) {
				String roomHsName = roomH.getHashtag().getHashtagName();
				tempRoomHsNameList.add(roomHsName);
			}
			temp.put("roomHash", tempRoomHsNameList);
			lst.add(temp);
		}
		
		result.put("roomList",lst);
		return result;
	}

	//유저 입장할 때 participation 1 증가
	@Override
	public void addParticipation(Room room) throws SQLException {
		roomQueryRepository.increaseParticipationCount(room);
	}

	//유저 퇴장할 때 participation 1 감소
	@Override
	public void decreaseParticipation(Room room) throws SQLException {
		roomQueryRepository.decreaseParticipationCount(room);

	}

	@Override
	public boolean enableJoinRoom(int roomNum) throws SQLException {
		Room room = roomRepository.findById(roomNum).get();

//		if (room.isRoomActive() && (room.getRoomCapacity() > room.getRoomParticipationCount()))
		if (room.getRoomCapacity() > room.getRoomParticipationCount())

			return true;
		else return false;
	}

	@Override
	public long addThumbnail(int roomNum, String filename) throws SQLException {
		return roomQueryRepository.addThumbnailonRoom(roomNum, filename);
	}

	@Override
	public int hasPw(int roomNum) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(roomNum);

		if (oRoom.isPresent()) {
			Room room = oRoom.get();
			if (room.isRoomHasPw()) {
				return 1;
			} else {
				return 2;
			}
		}
		return 0;
	}

	@Override
	public int forcedExitUser(String reqUserId, String userNickname, int roomNum) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(roomNum);
		Optional<User> oUser = userRepository.findByUserNickname(userNickname);
		Optional<User> oReqUser = userRepository.findByUserId(reqUserId);
		if (oReqUser.isPresent() && oRoom.isPresent() && oUser.isPresent()) {
			if (oRoom.get().getUserNum() == oReqUser.get().getUserNum()) {
				Room room = oRoom.get();
				RoomForcedExit forcedExitUser = new RoomForcedExit(userNickname, room);
				roomForceExitRepository.save(forcedExitUser);
				String userId = oUser.get().getUserId();
//				participationService.exitRoom(userId, room.getRoomNum());

				return 1;
			} else return 0;
		} else return 0;
	}
}

