package com.ssafy.B310.dto;

import lombok.Data;

@Data
public class User {
	private int userNum;
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private String userNickname;
	
	public User(int userNum, String userId, String userPw, String userName, String userEmail, String userNickname) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userNickname = userNickname;
	}
	public User() {
		super();
	}
}
