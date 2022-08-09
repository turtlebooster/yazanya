package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.*;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.repository.RoomHashtagRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.RoomQueryRepository;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.repository.UserRepository;
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

//	@Autowired
//	RoomHashtag roomHashtag;

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
			if(room.getRoomName() != null) r.setRoomName(room.getRoomName());
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
		}
		else return null;
	}

	// 해쉬태그 추천 목록
	public Map<String, Object> getRecommendHashtagList(List<Integer> hashtagNumList) {
		List<Room> roomList = roomQueryRepository.findRecommendRoom(hashtagNumList);
		Map<String, Object> result = new HashMap<>();
		List<String> roomHsNameList = new ArrayList<>();
		result.put("roomList", roomList);

		if (roomList.size() == 0) {
			roomList = roomRepository.findAll();
			result.put("roomList", roomList);
		}
		for (Room r: roomList) {
			int roomN = r.getRoomNum();
			List<RoomHashtag> roomHs = roomHashtagRepository.findByRoom(r);

			for (RoomHashtag roomH: roomHs) {
				System.out.println(roomH);
				String roomHsName = roomH.getHashtag().getHashtagName();
				roomHsNameList.add(roomHsName);
				result.put("roomHash", roomHsNameList);

//				roomList.add(roomH);
			}
		}
//		return roomList;
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
		
		if(room.isRoomActive() && (room.getRoomCapacity() > room.getRoomParticipationCount()))
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
}

