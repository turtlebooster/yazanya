package com.ssafy.B310.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Participation> participationList;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Todo> todo;
	
	@OneToOne
	@JoinColumn(name="room_num")
	@JsonIgnore
	private Room room;
	
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

	public User(int userNum, String userId, String userNickname) {
		this.userNum = userNum;
		this.userId = userId;
		this.userNickname = userNickname;
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
				", participationList=" + participationList +
				", todo=" + todo +
				'}';
	}
}
