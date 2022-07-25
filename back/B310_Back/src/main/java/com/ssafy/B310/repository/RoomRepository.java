package com.ssafy.B310.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>, JpaSpecificationExecutor<Room> {
	
	Page<Room> findAll(Specification<Room> spec, Pageable pageable);
	
	
	Optional<Room> findByRoomName(String roomName);
	
	@Query(value = "select * \r\n" + 
			"  from room r\r\n" + 
			" where r.room_num in (SELECT rh.room_num \r\n" + 
			"					    FROM room_hashtag rh \r\n" + 
			"                       WHERE rh.room_num in (:hashtagNumList))")
	List<Room> findByRoomByHashtagNum(@Param("hashtagNumList") List<Integer> hashtagNumList);
}
