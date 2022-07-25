package com.ssafy.B310.repository;


import com.ssafy.B310.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

//    @Transactional
////    List<Participation> findByroomNum(int room_num);
//    List<Participation> findByroom_num(int room_num);
//
//    @Transactional
//    List<Participation> findByuserNum(int room_num);
}
