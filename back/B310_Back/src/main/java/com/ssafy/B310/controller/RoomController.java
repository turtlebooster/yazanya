package com.ssafy.B310.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.service.HashtagService;
import com.ssafy.B310.service.JwtService;
import com.ssafy.B310.service.ParticipationHistoryService;
import com.ssafy.B310.service.ParticipationService;
import com.ssafy.B310.service.RoomHashtagService;
import com.ssafy.B310.service.RoomService;
import com.ssafy.B310.service.UserService;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    RoomService roomservice;

    @Autowired
    ParticipationService participationservice;
    
    @Autowired
    ParticipationHistoryService participationHistoryService; 
    
    @Autowired
    UserService userService;
    
    @Autowired
    HashtagService hashtagService;
    
    @Autowired
    RoomHashtagService roomHashtagService;
    
    @Autowired
    JwtService jwtService;
    
    // 방 생성
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody Room room) throws SQLException{

        int cnt = roomservice.createRoom(room);

        if(cnt!=0) return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 방 필터링
    @PostMapping("/filter")
    public ResponseEntity<?> filterRoom(@RequestBody Map<String, Integer> params) throws SQLException{
    	List<Room> roomList = roomservice.filterRoom(params);
    	
    	if(!roomList.isEmpty())
    		return new ResponseEntity<List<Room>>(roomList, HttpStatus.OK);
    	else
    		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR); 
    }
    
    // 방 하나 가져오기
    @GetMapping("/{roomNum}")
    public ResponseEntity<?> getRoom(@PathVariable int roomNum) throws SQLException{
    	Room room = roomservice.getRoom(roomNum);
    	
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("room", room);
		resultMap.put("message", SUCCESS);
    	
//    	return null;
    	if(room != null) {
    		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    	}
    	else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 방 삭제
    @DeleteMapping("/{roomNum}")
    public ResponseEntity<?> removeRoom(@PathVariable int roomNum) throws SQLException{

        int cnt = roomservice.removeRoom(roomNum);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 방 정보 업데이트
    @PutMapping
    public ResponseEntity<?> updateRoom(@RequestBody Room room) throws SQLException{

        int cnt = roomservice.updateRoom(room);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //방에 입장할 수 있다면 success / 인원이 다 차서or방장이 방을 잠궈서 입장할 수 없다면 fail
    @GetMapping("/active")
    public ResponseEntity<?> enableJoinRoom(@RequestParam int roomNum) throws SQLException {
    	boolean result = roomservice.enableJoinRoom(roomNum);
    	
    	if(result) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

    //방에 비밀번호 쳐서 맞으면 입장
    @Transactional
    @PostMapping("/{roomNum}")
    public ResponseEntity<?> joinRoom(@RequestBody Room room, @RequestParam int roomNum, HttpServletRequest request) throws SQLException {
    	String userId = jwtService.getUserID(request.getHeader("access-token"));
    	Room r = roomservice.getRoom(roomNum);    	
    	
        int cnt = 0;
        if((r.getRoomPw() == room.getRoomPw()) && roomservice.enableJoinRoom(roomNum)) {
        	cnt = participationservice.joinRoom(userId, r);
        }

        if(cnt==1) {
        	roomservice.addParticipation(r);
        	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    //hashtag num을 넘겨주면 해당 번호에 맞는 hashtag를 찾아옴
    //넘겨받은 hashtag를 room의 hashtag set에 저장
    @PostMapping("/hashtag")
    public ResponseEntity<?> addHashtag(@RequestParam int roomNum, @RequestParam int hashtagNum) throws SQLException {
    	Hashtag hashtag = hashtagService.getHashtag(hashtagNum);
    	Room room = roomservice.getRoom(roomNum);
   
    	RoomHashtag roomHt = new RoomHashtag(room, hashtag);
    	
    	int cnt = roomHashtagService.addRoomHashtag(roomHt);
		if(cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	    else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //해쉬태그 목록
    @GetMapping("/hashtag")
    public ResponseEntity<?> getHashtagList(@RequestParam int roomNum) throws SQLException {
    	Room room = roomservice.getRoom(roomNum);
    	
    	List<RoomHashtag> set = roomHashtagService.getRoomHashtagBy(room);
    	if(set.isEmpty()) {
    		System.out.println("list 비어있음");
    		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
//    	System.out.println(set.size());
    	for(RoomHashtag tag : set) {
    		System.out.println(tag.getHashtag().getHashtagName());
    	}
    	
    	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    
    //해쉬태그 삭제
    @DeleteMapping("/hashtag")
    public ResponseEntity<?> deleteHashtag(@RequestParam int roomNum, @RequestParam int hashtagNum) throws SQLException {
    	Room room = roomservice.getRoom(roomNum);
    	Hashtag hashtag = hashtagService.getHashtag(hashtagNum);
    	
    	int cnt = roomHashtagService.deleteRoomHashtag(room, hashtag);
    	
		if(cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	    else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 해쉬태그로 추천 방 리스트
    @GetMapping("/recommend")
    public ResponseEntity<?> recommendRoom(@RequestParam(value="hashtagNum", required=false, defaultValue="") List<Integer> hashtagNumList) {
    	return new ResponseEntity<List<Room>>(roomservice.getRecommendHashtagList(hashtagNumList), HttpStatus.OK);
    }
    // 유저 퇴장
    @DeleteMapping("/exit/{roomNum}")
    public ResponseEntity<?> exitRoom(@RequestBody Map<String, String> params , @PathVariable int roomNum ) throws SQLException {
        String userId = params.get("userId");
        int cnt = participationservice.exitRoom(userId, roomNum);

        System.out.println(userId);
        Room room = roomservice.getRoom(roomNum);
        if(cnt==1)  {
        	roomservice.decreaseParticipation(room);
        	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 입장 유저 리스트 반환
    @GetMapping("/join/{roomNum}")
    public ResponseEntity<?> joinedUser(@PathVariable int roomNum) throws SQLException {

        List joinedUserList = participationservice.joinedUser(roomNum);

//        return new ResponseEntity<List<Participation>>(joinedUserList, HttpStatus.OK);
        return new ResponseEntity<List<User>>(joinedUserList, HttpStatus.OK);

    }
    
    // 전에 방문했던 방 목록 반환
    @GetMapping("/history")
    public ResponseEntity<?> getRoomHistory(HttpServletRequest request) throws SQLException {
    	String userId = jwtService.getUserID(request.getHeader("access-token"));
    	System.out.println(userId);
    	return new ResponseEntity<List<Room>>(participationHistoryService.getRoomHistoryList(userId), HttpStatus.OK);
    }
}
