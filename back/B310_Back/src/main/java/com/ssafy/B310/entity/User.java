package com.ssafy.B310.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@DynamicInsert
@Builder
@ToString
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userNum;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String userPw;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false, unique = true)
	private String userEmail;

	@Column(nullable = false)
	private String userNickname;

	@Column(nullable = false)
	private int userStatusNum;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<Participation> participationList;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<Todo> todo;

	@Column
	@ColumnDefault("0")
	private int userRoomCount;

	// 자기소개
	@Column
	private String profileSelfIntroduce;

	// 프로필 사진(링크)
	@Column
	private String profilePictureLink;

	// 총 공부 시간(분 기준)

	@Column
	private int profileTotalStudyTime;

	// 랭크
	@Column
	private int profileRank;

	// 소속
	@Column
	private String profileBelongTo;

	public User(String userId, String userPw, String userName, String userEmail, String userNickname,
				int userStatusNum) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userNickname = userNickname;
		this.userStatusNum = userStatusNum;
	}

	public User() {
	}

	public User(String userId, String userNickname) {
		this.userId = userId;
		this.userNickname = userNickname;
	}
}
