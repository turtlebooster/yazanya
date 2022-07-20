/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ssafy.webrtc;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * Map of users registered in the system. This class has a concurrent hash map to store users, using
 * its name as key in the map.
 * 
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @author Micael Gallego (micael.gallego@gmail.com)
 * @authos Ivan Gracia (izanmail@gmail.com)
 * @since 4.3.1
 */
public class UserRegistry {
  
  // 유저 정보에 따른 세션을 저장하기 위한 hashMap
  // 유저 이름에 따른 hashMap
  private final ConcurrentHashMap<String, UserSession> usersByName = new ConcurrentHashMap<>();
  // 유저 세션ID(웹소켓 세션) 에 따른 hashMap
  private final ConcurrentHashMap<String, UserSession> usersBySessionId = new ConcurrentHashMap<>();
  
  // 유저 세션 등록
  public void register(UserSession user) {
	// Map 에 세션 등록  
    usersByName.put(user.getName(), user);
    usersBySessionId.put(user.getSession().getId(), user);
  }
  // 유저명으로 유저세션 가져오기
  public UserSession getByName(String name) {
    return usersByName.get(name);
  }
  // 웹소켓 세션으로 유저 세션(유저정보) 가져오기
  public UserSession getBySession(WebSocketSession session) {
    return usersBySessionId.get(session.getId());
  }

  public boolean exists(String name) {
    return usersByName.keySet().contains(name);
  }

  public UserSession removeBySession(WebSocketSession session) {
    final UserSession user = getBySession(session);
    usersByName.remove(user.getName());
    usersBySessionId.remove(session.getId());
    return user;
  }

}
