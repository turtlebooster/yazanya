package com.ssafy.B310.repository;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomHashtag;

@Repository
public interface RoomHashtagRepository extends JpaRepository<RoomHashtag, Integer> {

	List<RoomHashtag> findByRoom(Room room);
	
	@Transactional
	int deleteByRoomAndHashtag(Room room, Hashtag hashtag);
	int countByRoomAndHashtag(Room room, Hashtag hashtag);

	List<Room> findByHashtagHashtagNumIn(List<Integer> HashtagNum);
	List<RoomHashtag> findByHashtagIn(List<Hashtag> HashtagList);

}
