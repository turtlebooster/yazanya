package com.ssafy.B310.entity;

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
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
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
//	private List<Todo> todo = new ArrayList<>();
	
}
