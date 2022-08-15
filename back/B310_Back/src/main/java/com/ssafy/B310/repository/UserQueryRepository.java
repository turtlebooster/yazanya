package com.ssafy.B310.repository;

import static com.ssafy.B310.entity.QUser.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.B310.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {
	
	@Autowired
	UserRepository userRepo;
	
	public final JPAQueryFactory queryFactory;
	
	public void setRank(User userR) {
		int totalUser = queryFactory.selectFrom(user).fetch().size();
				
		List<User> list = queryFactory.selectFrom(user).orderBy(user.profileTotalStudyTime.desc()).fetch();
		
		double grade = (list.indexOf(userR) / (double)totalUser) * 100;
		
		if(grade <= 10) {
			userR.setProfileRank("마스터");
		} else if(grade <= 20) {
			userR.setProfileRank("다이아몬드");
		} else if(grade <= 30) {
			userR.setProfileRank("플래티넘");
		} else if(grade <= 40) {
			userR.setProfileRank("골드");
		} else if(grade <= 50) {
			userR.setProfileRank("실버");
		} else {
			userR.setProfileRank("브론즈");
		}
		
		userRepo.save(userR);
	}
}
