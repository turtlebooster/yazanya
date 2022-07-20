package com.ssafy.B310.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	Optional<Room> findByRoomName(String roomName);
}
