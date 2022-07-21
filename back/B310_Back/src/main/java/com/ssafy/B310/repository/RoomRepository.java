package com.ssafy.B310.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>, JpaSpecificationExecutor<Room> {
	
	//조건 검색용 메서드
	Page<Room> findAll(Specification<Room> spec, Pageable pageable);
	
	
	Optional<Room> findByRoomName(String roomName);
}
