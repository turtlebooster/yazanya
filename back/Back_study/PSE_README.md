# 제목 없음

---

# 왜 WebSocket을 사용하는가

## 기존 http 통신의 채팅 기능 방식

1. client[1]이 server에게 메세지 전송
2. server가 메세지를 client[2]에게 전송
3. client[2]는 자신에게 온 메세지 확인

## 해당 방식의 문제점

- http 통신은 client가 요청을 하면 server가 응답하는 방식
- server의 입장 : client[2]에게 메세지 도착을 알리려고 해도, client[2]에게 요청이 없었기에 일방적인 응답을 보낼 수 없음
- client[2]의 입장 : 메세지가 도착했는지 아닌지를 판단할 수 없기에 server에게 메세지를 보내달라고 요청할 수 없음

---

# JPA

## JPA를 왜 써야 하는가?

- SQL을 직접 다루면 반복적인 CRUD SQL 작성과 SQL에 객체를 매핑하는 코드를 작성하는데 시간이 많이 걸림
- JPA는 객체를 전달만 하면 되므로 SQL을 작성하는 반복적인 일을 JPA가 대신 처리해주어 생산성 향상

## JPA 주요 어노테이션 종류

| 엔티티명 | 설명 |
| --- | --- |
| @Entity | 클래스를 테이블과 매핑한다고 JPA에게 알려줌 |
| @Table | Entity클래스에 매핑할 테이블 정보를 알려줌. 생략할 경우 클래스 이름을 테이블 이름으로 매핑 |
| @Id | Entity클래스의 필드를 테이블의 pk에 매핑 |
| @Column | 필드를 컬럼에 매핑 |
| @GeneratedValue | auto increment 컬럼임을 알려줌 |
| 매핑 정보가 없는 필드 | 필드명을 사용해서 컬럼명으로 매핑. ex)age라는 필드명에 매핑 정보가 없다면 age 컬럼에 매핑 |

controller ⇒ 자바 로직 수행

entity ⇒ 테이블 정보 설정

repository ⇒ 쿼리 정보 설정

- 외부에서 controller를 호출하면 repository가 entity를 가지고 처리한 후 반환
- repository의 경우, JpaRepository를 extends받으면 해당 Repository로 JPA의 대부분의 자동 쿼리 기능을 수행할 수 있다.

## JPA의 CRUD

- 저장 : jpa.persist(Student)
- 조회 : Student student = jpa.find(studentId)
- 수정 : student.setName(”홍길동”)
- 삭제 : jpa.remove(student)

## JPA 기본 사용 예시

- Entity 매니저를 애플리케이션 전체에서 한 개 생성하고 공유하여 사용

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//이름이 jpa 인 매니저 팩토리 생성

EntityManager em = emf.createEntityManager();
//매니저 팩토리에서 Entity 매니저를 생성.
//jpa 대부분의 기능은 Entity 매니저가 제공한다
//Entity 매니저는 db 커넥션과 관계가 있다

em.persist(member); //데이터 저장

String userId = "user1";
Member member = em.find(Member.class, userId); //데이터 조회

Member member = em.find(Member.class, userId);
member.setName("변경할 이름"); //데이터 수정

em.remove(member); //데이터 삭제
```

---

# 채팅 시스템 구현하기

## 프로젝트 기본 설정

1. 프로젝트 생성할 때 WebSocket 추가
2. application.properties 설정
    
    ```java
    #Tomcat Server Setting
    server.port=#원하는 포트 번호
    
    #JSP, HTML ModelAndView Path Setting
    spring.mvc.view.prefix=/WEB-INF/jsp/ #사용할 페이지 경로
    spring.mvc.view.suffix=.jsp
    
    #JSP to Modify Not Restart Server
    server.servlet.jsp.init-parameters.development=true
    ```
    
3. pom.xml에 dependency 추가
    
    ```java
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
    implementation 'javax.servlet:jstl'
    ```
    

## 프로젝트 코드 작성

1. controller 패키지를 추가하고, controller java 파일을 생성한다.
2. chat 파일을 넘겨주는 view 컨트롤러를 만든다.
    
    ```java
    package com.ssafy.chating.controller;
    
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.servlet.ModelAndView;
    
    @Controller
    public class MainController {
    	
    	@RequestMapping("/chat")
    	public ModelAndView chat() {
    		ModelAndView mv = new ModelAndView();
    		mv.setViewName("chat");
    		return mv;
    	}
    }
    ```
    
3. config 패키지와 handler 패키지를 추가한다.
4. SocketHandler.java를 작성한다.
    
    ```java
    package com.ssafy.chating.handler;
    
    import java.util.HashMap;
    
    import org.springframework.stereotype.Component;
    import org.springframework.web.socket.CloseStatus;
    import org.springframework.web.socket.TextMessage;
    import org.springframework.web.socket.WebSocketSession;
    import org.springframework.web.socket.handler.TextWebSocketHandler;
    
    @Component
    public class SocketHandler extends TextWebSocketHandler {
    	
    	@Override
    	public void handleTextMessage(WebSocketSession session, TextMessage message) {
    		//메시지 발송
    		//메세지를 수신하면 실행된다.
    		//메시지 발송
    		String msg = message.getPayload();
    		for(String key : sessionMap.keySet()) {
    			WebSocketSession wss = sessionMap.get(key);
    			try {
    				wss.sendMessage(new TextMessage(msg));
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	@Override
    	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    		//소켓 연결
    		//웹소켓 연결이 되면 동작한다.
    		super.afterConnectionEstablished(session);
    		sessionMap.put(session.getId(), session);
    	}
    	
    	@Override
    	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    		//소켓 종료
    		//웹소켓이 종료되면 동작한다.
    		sessionMap.remove(session.getId());
    		super.afterConnectionClosed(session, status);
    	}
    }
    ```
    
5. 구현체를 등록해주기 위해 WebSocketConfig.java를 작성한다
    
    ```java
    package com.ssafy.chating.config;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.socket.config.annotation.EnableWebSocket;
    import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
    import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
    
    import com.psw.chating.handler.SocketHandler;
    
    @Configuration
    @EnableWebSocket
    public class WebSocketConfig implements WebSocketConfigurer{
    
    	@Autowired
    	SocketHandler socketHandler;
    	
    	@Override
    	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    		registry.addHandler(socketHandler, "/chating");
    	}
    }
    ```
    
6. 채팅 페이지인 chat.jsp를 작성한다.
    - chat.jsp 코드
        
        ```html
        <%@ page language="java" contentType="text/html; charset=UTF-8"
            pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>
        <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <meta charset="UTF-8">
        	<title>Chating</title>
        	<style>
        		*{
        			margin:0;
        			padding:0;
        		}
        		.container{
        			width: 500px;
        			margin: 0 auto;
        			padding: 25px
        		}
        		.container h1{
        			text-align: left;
        			padding: 5px 5px 5px 15px;
        			color: #FFBB00;
        			border-left: 3px solid #FFBB00;
        			margin-bottom: 20px;
        		}
        		.chating{
        			background-color: #000;
        			width: 500px;
        			height: 500px;
        			overflow: auto;
        		}
        		.chating p{
        			color: #fff;
        			text-align: left;
        		}
        		input{
        			width: 330px;
        			height: 25px;
        		}
        		#yourMsg{
        			display: none;
        		}
        	</style>
        </head>
        
        <script type="text/javascript">
        	var ws;
        
        	function wsOpen(){
        		ws = new WebSocket("ws://" + location.host + "/chating");
        		wsEvt();
        	}
        		
        	function wsEvt() {
        		ws.onopen = function(data){
        			//소켓이 열리면 초기화 세팅하기
        		}
        		
        		ws.onmessage = function(data) {
        			var msg = data.data;
        			if(msg != null && msg.trim() != ''){
        				$("#chating").append("<p>" + msg + "</p>");
        			}
        		}
        
        		document.addEventListener("keypress", function(e){
        			if(e.keyCode == 13){ //enter press
        				send();
        			}
        		});
        	}
        
        	function chatName(){
        		var userName = $("#userName").val();
        		if(userName == null || userName.trim() == ""){
        			alert("사용자 이름을 입력해주세요.");
        			$("#userName").focus();
        		}else{
        			wsOpen();
        			$("#yourName").hide();
        			$("#yourMsg").show();
        		}
        	}
        
        	function send() {
        		var uN = $("#userName").val();
        		var msg = $("#chatting").val();
        		ws.send(uN+" : "+msg);
        		$('#chatting').val("");
        	}
        </script>
        <body>
        	<div id="container" class="container">
        		<h1>채팅</h1>
        		<div id="chating" class="chating">
        		</div>
        		
        		<div id="yourName">
        			<table class="inputTable">
        				<tr>
        					<th>사용자명</th>
        					<th><input type="text" name="userName" id="userName"></th>
        					<th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
        				</tr>
        			</table>
        		</div>
        		<div id="yourMsg">
        			<table class="inputTable">
        				<tr>
        					<th>메시지</th>
        					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
        					<th><button onclick="send()" id="sendBtn">보내기</button></th>
        				</tr>
        			</table>
        		</div>
        	</div>
        </body>
        </html>
        ```
        

## 채팅방 설정

1. 방의 정보를 담아둘 Room 객체를 생성한다
    
    ```java
    package com.ssafy.chating.vo;
    
    public class Room {
    	int roomNumber;
    	String roomName;
    	
    	public int getRoomNumber() {
    		return roomNumber;
    	}
    	public void setRoomNumber(int roomNumber) {
    		this.roomNumber = roomNumber;
    	}
    	public String getRoomName() {
    		return roomName;
    	}
    	public void setRoomName(String roomName) {
    		this.roomName = roomName;
    	}
    	
    	@Override
    	public String toString() {
    		return "Room [roomNumber=" + roomNumber + ", roomName=" + roomName + "]";
    	}	
    }
    ```
    
2. 방 접근, 방 생성, 방 정보 가져오는 메소드 작성
    
    ```java
    package com.ssafy.chating.controller;
    
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.stream.Collectors;
    
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.ResponseBody;
    import org.springframework.web.servlet.ModelAndView;
    
    import com.ssafy.chating.vo.Room;
    
    @Controller
    public class MainController {
    
    	//현재는 DB에 데이터를 담을 것이 아니기에 방의 정보를 담기 위해 List<Room> 컬렉션 생성
    	List<Room> roomList = new ArrayList<Room>();
    	static int roomNumber = 0;
    	
    	@RequestMapping("/chat")
    	public ModelAndView chat() {
    		ModelAndView mv = new ModelAndView();
    		mv.setViewName("chat");
    		return mv;
    	}
    	
    	/**
    	 * 방 페이지
    	 * @return
    	 */
    	@RequestMapping("/room")
    	public ModelAndView room() {
    		ModelAndView mv = new ModelAndView();
    		mv.setViewName("room");
    		return mv;
    	}
    	
    	/**
    	 * 방 생성하기
    	 * @param params
    	 * @return
    	 */
    	@RequestMapping("/createRoom")
    	public @ResponseBody List<Room> createRoom(@RequestParam HashMap<Object, Object> params){
    		String roomName = (String) params.get("roomName");
    		if(roomName != null && !roomName.trim().equals("")) {
    			Room room = new Room();
    			room.setRoomNumber(++roomNumber);
    			room.setRoomName(roomName);
    			roomList.add(room);
    		}
    		return roomList;
    	}
    	
    	/**
    	 * 방 정보가져오기
    	 * @param params
    	 * @return
    	 */
    	@RequestMapping("/getRoom")
    	public @ResponseBody List<Room> getRoom(@RequestParam HashMap<Object, Object> params){
    		return roomList;
    	}
    	
    	/**
    	 * 채팅방
    	 * @return
    	 */
    	@RequestMapping("/moveChating")
    	public ModelAndView chating(@RequestParam HashMap<Object, Object> params) {
    		ModelAndView mv = new ModelAndView();
    		int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
    		
    		List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
    		if(new_list != null && new_list.size() > 0) {
    			mv.addObject("roomName", params.get("roomName"));
    			mv.addObject("roomNumber", params.get("roomNumber"));
    			mv.setViewName("chat");
    		}else {
    			mv.setViewName("room");
    		}
    		return mv;
    	}
    }
    ```
    
3. url 정보를 방번호에 따라 구분될 수 있도록 변경
    
    ```java
    package com.ssafy.chating.config;
    
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.socket.config.annotation.EnableWebSocket;
    import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
    import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
    
    import com.ssafy.chating.handler.SocketHandler;
    
    @Configuration
    @EnableWebSocket
    public class WebSocketConfig implements WebSocketConfigurer{
    
    	@Autowired
    	SocketHandler socketHandler;
    	
    	@Override
    	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    		registry.addHandler(socketHandler, "/chating/{roomNumber}");
    		//url에서 chating/ 이후 들어오는 {roomNumber}값이 방을 구분하는 값
    	}
    }
    ```
    
4. sockethandler를 변경한다 - **d번에서 하는 처리로 인해 방구분을 하고 해당 방에 존재하는 session값들에게만 메시지를 발송하여 구분**
    1. 세션을 관리하던 map객체에서 list, hashmap형태로 변경. hashmap의 value자료형도 WebSocketSession에서 Object형으로 변경
    2. 세션을 저장할때, 현재 접근한 방의 정보가 있는지 체크하고 존재하지 않으면 방의 번호를 입력 후 세션들을 담아주는 로직으로 변경
    3. 종료시에도 list컬랙션을 순회하면서 해당 키값의 세션들을 삭제하도록 변경
    4. 메시지를 발송하는 handleTextMessage메소드에서는 현재의 방번호를 가져오고, 방정보+세션정보를 관리하는 rls리스트 컬랙션에서 데이터를 조회한 후에 해당 Hashmap을 임시 맵에 파싱하여 roomNumber의 키값을 제외한 모든 세션키값들을 웹소켓을 통해 메시지를 보냄
    
    ```java
    package com.ssafy.chating.handler;
    
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    
    import org.json.simple.JSONObject;
    import org.json.simple.parser.JSONParser;
    import org.json.simple.parser.ParseException;
    import org.springframework.stereotype.Component;
    import org.springframework.web.socket.CloseStatus;
    import org.springframework.web.socket.TextMessage;
    import org.springframework.web.socket.WebSocketSession;
    import org.springframework.web.socket.handler.TextWebSocketHandler;
    
    @Component
    public class SocketHandler extends TextWebSocketHandler {
    	
    	//HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
    	List<HashMap<String, Object>> rls = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions
    	
    	@Override
    	public void handleTextMessage(WebSocketSession session, TextMessage message) {
    		//메시지 발송
    		String msg = message.getPayload();
    		JSONObject obj = jsonToObjectParser(msg);
    		
    		String rN = (String) obj.get("roomNumber");
    		HashMap<String, Object> temp = new HashMap<String, Object>();
    		if(rls.size() > 0) {
    			for(int i=0; i<rls.size(); i++) {
    				String roomNumber = (String) rls.get(i).get("roomNumber"); //세션리스트의 저장된 방번호를 가져와서
    				if(roomNumber.equals(rN)) { //같은값의 방이 존재한다면
    					temp = rls.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
    					break;
    				}
    			}
    			
    			//해당 방의 세션들만 찾아서 메시지를 발송해준다.
    			for(String k : temp.keySet()) { 
    				if(k.equals("roomNumber")) { //다만 방번호일 경우에는 건너뛴다.
    					continue;
    				}
    				
    				WebSocketSession wss = (WebSocketSession) temp.get(k);
    				if(wss != null) {
    					try {
    						wss.sendMessage(new TextMessage(obj.toJSONString()));
    					} catch (IOException e) {
    						e.printStackTrace();
    					}
    				}
    			}
    		}
    	}
    	
    	@SuppressWarnings("unchecked")
    	@Override
    	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    		//소켓 연결
    		super.afterConnectionEstablished(session);
    		boolean flag = false;
    		String url = session.getUri().toString();
    		System.out.println(url);
    		String roomNumber = url.split("/chating/")[1];
    		int idx = rls.size(); //방의 사이즈를 조사한다.
    		if(rls.size() > 0) {
    			for(int i=0; i<rls.size(); i++) {
    				String rN = (String) rls.get(i).get("roomNumber");
    				if(rN.equals(roomNumber)) {
    					flag = true;
    					idx = i;
    					break;
    				}
    			}
    		}
    		
    		if(flag) { //존재하는 방이라면 세션만 추가한다.
    			HashMap<String, Object> map = rls.get(idx);
    			map.put(session.getId(), session);
    		}else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
    			HashMap<String, Object> map = new HashMap<String, Object>();
    			map.put("roomNumber", roomNumber);
    			map.put(session.getId(), session);
    			rls.add(map);
    		}
    		
    		//세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
    		JSONObject obj = new JSONObject();
    		obj.put("type", "getId");
    		obj.put("sessionId", session.getId());
    		session.sendMessage(new TextMessage(obj.toJSONString()));
    	}
    	
    	@Override
    	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    		//소켓 종료
    		if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
    			for(int i=0; i<rls.size(); i++) {
    				rls.get(i).remove(session.getId());
    			}
    		}
    		super.afterConnectionClosed(session, status);
    	}
    	
    	private static JSONObject jsonToObjectParser(String jsonStr) {
    		JSONParser parser = new JSONParser();
    		JSONObject obj = null;
    		try {
    			obj = (JSONObject) parser.parse(jsonStr);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    		return obj;
    	}
    }
    ```
    
5. 방을 생성하고 목록을 보여주고, 참여할 수 있게 해주는 jsp 페이지 생성
    - room.jsp 코드
        
        ```jsx
        <%@ page language="java" contentType="text/html; charset=UTF-8"
            pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>
        <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <meta charset="UTF-8">
        	<title>Chating</title>
        	<style>
        		*{
        			margin:0;
        			padding:0;
        		}
        		.container{
        			width: 500px;
        			margin: 0 auto;
        			padding: 25px
        		}
        		.container h1{
        			text-align: left;
        			padding: 5px 5px 5px 15px;
        			color: #FFBB00;
        			border-left: 3px solid #FFBB00;
        			margin-bottom: 20px;
        		}
        		.chating{
        			background-color: #000;
        			width: 500px;
        			height: 500px;
        			overflow: auto;
        		}
        		.chating .me{
        			color: #F6F6F6;
        			text-align: right;
        		}
        		.chating .others{
        			color: #FFE400;
        			text-align: left;
        		}
        		input{
        			width: 330px;
        			height: 25px;
        		}
        		#yourMsg{
        			display: none;
        		}
        	</style>
        </head>
        
        <script type="text/javascript">
        	var ws;
        
        	function wsOpen(){
        		//웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
        		ws = new WebSocket("ws://" + location.host + "/chating/"+$("#roomNumber").val());
        		wsEvt();
        	}
        		
        	function wsEvt() {
        		ws.onopen = function(data){
        			//소켓이 열리면 동작
        		}
        		
        		ws.onmessage = function(data) {
        			//메시지를 받으면 동작
        			var msg = data.data;
        			if(msg != null && msg.trim() != ''){
        				var d = JSON.parse(msg);
        				if(d.type == "getId"){
        					var si = d.sessionId != null ? d.sessionId : "";
        					if(si != ''){
        						$("#sessionId").val(si); 
        					}
        				}else if(d.type == "message"){
        					if(d.sessionId == $("#sessionId").val()){
        						$("#chating").append("<p class='me'>나 :" + d.msg + "</p>");	
        					}else{
        						$("#chating").append("<p class='others'>" + d.userName + " :" + d.msg + "</p>");
        					}
        						
        				}else{
        					console.warn("unknown type!")
        				}
        			}
        		}
        
        		document.addEventListener("keypress", function(e){
        			if(e.keyCode == 13){ //enter press
        				send();
        			}
        		});
        	}
        
        	function chatName(){
        		var userName = $("#userName").val();
        		if(userName == null || userName.trim() == ""){
        			alert("사용자 이름을 입력해주세요.");
        			$("#userName").focus();
        		}else{
        			wsOpen();
        			$("#yourName").hide();
        			$("#yourMsg").show();
        		}
        	}
        
        	function send() {
        		var option ={
        			type: "message",
        			roomNumber: $("#roomNumber").val(),
        			sessionId : $("#sessionId").val(),
        			userName : $("#userName").val(),
        			msg : $("#chatting").val()
        		}
        		ws.send(JSON.stringify(option))
        		$('#chatting').val("");
        	}
        </script>
        <body>
        	<div id="container" class="container">
        		<h1>${roomName}의 채팅방</h1>
        		<input type="hidden" id="sessionId" value="">
        		<input type="hidden" id="roomNumber" value="${roomNumber}">
        		
        		<div id="chating" class="chating">
        		</div>
        		
        		<div id="yourName">
        			<table class="inputTable">
        				<tr>
        					<th>사용자명</th>
        					<th><input type="text" name="userName" id="userName"></th>
        					<th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
        				</tr>
        			</table>
        		</div>
        		<div id="yourMsg">
        			<table class="inputTable">
        				<tr>
        					<th>메시지</th>
        					<th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
        					<th><button onclick="send()" id="sendBtn">보내기</button></th>
        				</tr>
        			</table>
        		</div>
        	</div>
        </body>
        </html>
        ```
