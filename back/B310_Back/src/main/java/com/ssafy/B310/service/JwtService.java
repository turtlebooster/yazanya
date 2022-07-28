package com.ssafy.B310.service;

import java.util.Map;

public interface JwtService {
	// 토큰 생성
	<T> String create(String key, T data, String subject);	
	Map<String, Object> get(String key);
	// 토큰으로부터 userID 가져오기
	String getUserID();
	boolean isUsable(String jwt);
	// 토큰 갱신
	<T> String refresh(String jwt);
	
}
