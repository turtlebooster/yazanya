package com.ssafy.B310.repository;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomForcedExit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomForceExitRepository extends JpaRepository<RoomForcedExit, Integer> {

    @Transactional
    List<RoomForcedExit> findByRoom_roomName(String roomName);

    @Transactional
    int deleteByRoom_roomNum(int roomNum);

}
