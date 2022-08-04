package com.ssafy.B310.repository;

import static com.ssafy.B310.entity.QParticipationHistory.participationHistory;
//import static com.ssafy.B310.entity.QUser.user;
import static com.ssafy.B310.entity.QRoom.room;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.B310.entity.ParticipationHistory;
import com.ssafy.B310.entity.Room;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ParticipationHistoryQueryRepository {
	private final JPAQueryFactory queryFactory;
	
	public List<ParticipationHistory> findParticipationHistory(String userId, int roomNum) {
		return queryFactory
				.selectFrom(participationHistory)
				.where(participationHistory.user.userId.eq(userId), participationHistory.room.roomNum.eq(roomNum))
				.fetch();
				
	}
	
	public List<Room> findParticipationHistoryByUserId(String userId) {
		return queryFactory
				.selectFrom(room)
				.where(participationHistory.user.userId.eq(userId))
				.orderBy(participationHistory.participationHistoryUpdateTime.desc())
				.fetch();
	}
}
