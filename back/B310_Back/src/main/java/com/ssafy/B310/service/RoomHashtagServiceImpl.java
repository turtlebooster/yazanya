package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.repository.RoomHashtagRepository;

@Service
public class RoomHashtagServiceImpl implements RoomHashtagService{
	@Autowired
	RoomHashtagRepository rHtRepo;

	@Override
	public List<RoomHashtag> getRoomHashtagBy(Room room) throws SQLException {
		return rHtRepo.findByRoom(room);
	}

	@Override
	public int addRoomHashtag(RoomHashtag rHt) throws SQLException {
		RoomHashtag roomhashtag = rHtRepo.save(rHt);
		
		if(roomhashtag != null)
			return 1;
		else return 0;
	}

	@Override
	public int deleteRoomHashtag(Room room, Hashtag hashtag) throws SQLException {
		return rHtRepo.deleteByRoomAndHashtag(room, hashtag);
	}
}
