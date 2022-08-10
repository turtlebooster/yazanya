package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;

public interface RoomService {
	// 방 생성
	public int createRoom(Room room, int userNum) throws SQLException;

	// 방 검색
	public List<Room> filterRoom(Map<String, Integer> params) throws SQLException;
	// 방 정보 수정
	public int updateRoom(Room room) throws SQLException;

	// 방 삭제
	public int removeRoom(int roomNum, int userNum) throws SQLException;
	// 방 하나 가져오기
	public Room getRoom(int roomNum) throws SQLException;

	
	//해쉬태그 추천으로 목록 불러오기
	Map<String, Object> getRecommendHashtagList(List<Integer> hashtagNumList);
	
	//유저 입장할 때 participation 1 증가
	public void addParticipation(Room room) throws SQLException;
	
	//유저 퇴장할 때 participation 1 감소
	public void decreaseParticipation(Room room) throws SQLException;
	
	//방에 입장할 수 있는지 없는지를 반환
	public boolean enableJoinRoom(int roomNum) throws SQLException;
	
	//방 썸네일 등록하기
	public long addThumbnail(int roomNum, String filename) throws SQLException;
	
	//방 비밀번호 유무  0: 방 이상 1: 비밀번호 있음 2: 비밀번호 없음 
	public int hasPw(int roomNum) throws SQLException;

	public int forcedExitUser(String reqUserId,String userId, String roomName) throws SQLException;
}
