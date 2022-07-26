package com.ssafy.B310.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.B310.entity.Room;

import lombok.RequiredArgsConstructor;

import static com.ssafy.B310.entity.QRoom.room;
import static com.ssafy.B310.entity.QRoomHashtag.roomHashtag;

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
}
