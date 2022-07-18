package com.ssafy.B310.service;

import java.util.Map;

public interface JwtService {
	
	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	String getUserID();
	boolean isUsable(String jwt);
	
}
