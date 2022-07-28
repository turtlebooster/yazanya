package com.ssafy.B310.repository;

import static com.ssafy.B310.entity.QRoom.room;
import static com.ssafy.B310.entity.QRoomHashtag.roomHashtag;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.B310.entity.Room;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Repository
public class RoomQueryRepository {
	private final JPAQueryFactory queryFactory;
	
	public List<Room> findRecommendRoom(List<Integer> hashtagNumList) {
		return 	queryFactory
				.selectFrom(room)
				.where(room.roomNum.in(
						JPAExpressions
							.select(roomHashtag.room.roomNum)
							.from(roomHashtag)
							.where(roomHashtag.hashtag.hashtagNum.in(hashtagNumList))))
				.fetch();
	}
	
	//participation count + 1
	@Transactional
	@Modifying
	public void increaseParticipationCount(Room roomUpdate) {
		queryFactory
			.update(room)
			.where(room.roomNum.eq(roomUpdate.getRoomNum()))
			.set(room.participationCount, room.participationCount.add(1))
			.execute();
	}
	
	//participation count - 1
	@Transactional
	@Modifying
	public void decreaseParticipationCount(Room roomUpdate) {
		queryFactory
			.update(room)
			.where(room.roomNum.eq(roomUpdate.getRoomNum()))
			.set(room.participationCount, room.participationCount.subtract(1))
			.execute();
	}
}
