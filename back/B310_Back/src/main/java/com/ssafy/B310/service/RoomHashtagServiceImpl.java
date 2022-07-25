package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.repository.RoomHashtagRepository;

@Service
public class RoomHashtagServiceImpl implements RoomHashtagService{
	@Autowired
	RoomHashtagRepository rHtRepo;

	@Override
	public List<RoomHashtag> getListRoomHashtag() throws SQLException {
		return rHtRepo.findAll();
	}

	@Override
	public int addRoomHashtag(RoomHashtag rHt) throws SQLException {
		rHtRepo.save(rHt);
		return 1;
	}
}
