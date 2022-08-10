package com.ssafy.B310.service;

import java.util.Map;

public interface JwtService {
	// Access 토큰 생성
	String createAccessToken(String userId);
	
	// Refresh 토큰 생성
	String createRefreshToken(String userId);
	
	Map<String, Object> get(String key);
	// 토큰으로부터 userID 가져오기
	String getUserID(String jwt);
	boolean isUsable(String jwt);
	// 토큰 갱신
	<T> String refresh(String jwt);
	// 토큰으로부터 userNum 가져오기
	int getUserNum(String jwt);
	boolean isValidAccessToken(String token);
	boolean isValidRefreshToken(String token);
	
}
