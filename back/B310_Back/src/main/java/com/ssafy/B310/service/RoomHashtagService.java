package com.ssafy.B310.service;


import java.sql.SQLException;
import java.util.List;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomHashtag;

public interface RoomHashtagService {
	
	//방 해쉬태그 목록
	public List<RoomHashtag> getRoomHashtagBy(Room room) throws SQLException;
	
	//방 해쉬태그 추가
	public int addRoomHashtag(RoomHashtag rHt) throws SQLException;
	
	//방 해쉬태그 삭제
	public int deleteRoomHashtag(Room room, Hashtag hashtag) throws SQLException;
	
}
