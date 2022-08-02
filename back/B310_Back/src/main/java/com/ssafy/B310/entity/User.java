package com.ssafy.B310.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@DynamicInsert
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

	// 자기소개

	@Column
	private String profileSelfIntroduce;

	// 프로필 사진(링크)
	@Column
	private String profilePictureLink;

	// 총 공부 시간(분 기준)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date profileTotalStudyTime;
	// date로 넘기는 방법 아직 못찾아서 일단 int로 설정
	@Column
//	@ColumnDefault("/img/profile_default.jpg")
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

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Participation> participationList;
//	private List<Participation> participationList = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Todo> todo;

	public User(int userNum, String userId, String userNickname) {
		this.userNum = userNum;
		this.userId = userId;
		this.userNickname = userNickname;
	}

//	private List<Todo> todo = new ArrayList<>();

	public User(int userNum, String userId, String userPw, String userName, String userEmail, String userNickname, int userStatusNum, String profileSelfIntroduce, String profilePictureLink, int profileTotalStudyTime, int profileRank, String profileBelongTo, Set<Participation> participationList, Set<Todo> todo) {
		this.userNum = userNum;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userNickname = userNickname;
		this.userStatusNum = userStatusNum;
		this.profileSelfIntroduce = profileSelfIntroduce;
		this.profilePictureLink = profilePictureLink;
		this.profileTotalStudyTime = profileTotalStudyTime;
		this.profileRank = profileRank;
		this.profileBelongTo = profileBelongTo;
		this.participationList = participationList;
		this.todo = todo;
	}

	@Override
	public String toString() {
		return "User{" +
				"userNum=" + userNum +
				", userId='" + userId + '\'' +
				", userPw='" + userPw + '\'' +
				", userName='" + userName + '\'' +
				", userEmail='" + userEmail + '\'' +
				", userNickname='" + userNickname + '\'' +
				", userStatusNum=" + userStatusNum +
				", profileSelfIntroduce='" + profileSelfIntroduce + '\'' +
				", profilePictureLink='" + profilePictureLink + '\'' +
				", profileTotalStudyTime=" + profileTotalStudyTime +
				", profileRank=" + profileRank +
				", profileBelongTo='" + profileBelongTo + '\'' +
				", participationList=" + participationList +
				", todo=" + todo +
				'}';
	}
}
