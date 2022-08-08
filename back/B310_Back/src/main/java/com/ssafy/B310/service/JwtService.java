package com.ssafy.B310.service;

import java.util.Map;

public interface JwtService {
	// Access 토큰 생성
	<T> String createAccessToken(String key, T data, String subject);
	
	// Refresh 토큰 생성
	<T> String createRefreshToken(String key, T data, String subject);
	
	Map<String, Object> get(String key);
	// 토큰으로부터 userID 가져오기
	String getUserID(String jwt);
	boolean isUsable(String jwt);
	// 토큰 갱신
	<T> String refresh(String jwt);
	
}
