package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.specification.RoomSpecification;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	RoomSpecification roomSpecification;
	
	@Override
	public int createRoom(Room room) throws SQLException {
		// JPA save�� ��ȯ�Ǵ� ��ü�� �̹��� ������ room ����?
		Room newRoom = roomRepository.save(room);
		return newRoom.getRoomNum();
	}



	@Override
	public int updateRoom(Room room) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(room.getRoomNum()); 
		// �ش� ���� �������
		if (oRoom.isPresent()) {
			Room r = oRoom.get();
			// �ؽ��±״� ���⼭ ������Ʈ ����
			// �� ����� �˶����� ����
			r.setRoomName(room.getRoomName());
			r.setRoomStudyTime(room.getRoomStudyTime());
			r.setRoomRestTime(room.getRoomRestTime());
			roomRepository.save(r);
			return 1;
		}
		// �������
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
	public int removeRoom(int roomNum) throws SQLException {
		Optional<Room> oRoom = roomRepository.findById(roomNum);
		// �ش� ���� �������
		if (oRoom.isPresent()) {
			Room r = oRoom.get();
			roomRepository.deleteById(roomNum);
			return 1;
		}
		// �������
		return 0;
	}



	


}

