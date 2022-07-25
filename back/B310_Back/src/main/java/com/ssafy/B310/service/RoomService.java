package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;

public interface RoomService {
	// 방 생성
	public int createRoom(Room room) throws SQLException;
	// 방 조건 검색
	public List<Room> filterRoom(Map<String, Integer> params) throws SQLException;
	// 방 정보 수정
	public int updateRoom(Room room) throws SQLException;
	// 방 삭제
	public int removeRoom(int roomNum) throws SQLException;
	// 방 하나 가져오기
	public Room getRoom(int roomNum) throws SQLException;
	
	
	//해쉬태그
	//방에 해쉬태그 추가
	public int addHashtag(Hashtag hashtag) throws SQLException;
	//방에 해쉬태그 삭제
	public int removeHashtag(Hashtag hashtag) throws SQLException;
	//방 해쉬태그 목록
	public List<Hashtag> getListHashtag() throws SQLException;
	
	
	//방 해쉬태그 수정 -- 만들 이유가 없음
}
