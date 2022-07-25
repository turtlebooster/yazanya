package com.ssafy.B310.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomHashtag;

@Repository
public interface RoomHashtagRepository extends JpaRepository<RoomHashtag, Integer> {
	List<Room> findByHashtagHashtagNumIn(List<Integer> HashtagNum);
	List<RoomHashtag> findByHashtagIn(List<Hashtag> HashtagList);
}
