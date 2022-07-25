package com.ssafy.B310.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.ssafy.B310.service.ParticipationService;
import com.ssafy.B310.service.RoomHashtagService;
import com.ssafy.B310.service.RoomService;

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
    HashtagService hashtagService;
    
    @Autowired
    RoomHashtagService roomHashtagService;

    // 방 생성
    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody Room room) throws SQLException{

        int cnt = roomservice.createRoom(room);

        if(cnt!=0) return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 방 조회
    @GetMapping("/filter")
    public ResponseEntity<?> filterRoom(@RequestBody Map<String, Integer> params) throws SQLException{
        return new ResponseEntity<List<Room>>(roomservice.filterRoom(params), HttpStatus.OK);
    }

    // 방 삭제
    @GetMapping("/remove/{roomNum}")
    public ResponseEntity<?> removeRoom(@PathVariable("roomNum") int roomNum) throws SQLException{

        int cnt = roomservice.removeRoom(roomNum);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 방 정보 업데이트
    @PutMapping("/update")
    public ResponseEntity<?> updateRoom(@RequestBody Room room) throws SQLException{

        int cnt = roomservice.updateRoom(room);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 유저 입장

//    @PostMapping("/join/{roomNum}")
//    public ResponseEntity<?> joinRoom(@PathVariable("roomNum") int roomNum) throws SQLException {
//        User user = null;
//        int cnt = participationservice.joinRoom(new Participation(roomNum, user.userNum));
//
//        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    @PostMapping("/join/{roomNum}/{pw}")
    public ResponseEntity<?> joinRoom(@RequestBody User user , @PathVariable("roomNum") int roomNum, @PathVariable("pw") int pw) throws SQLException {
        Room room = roomservice.getRoom(roomNum);
        
        int cnt = 0;
        if(room.getRoomPw() == pw) {
        	cnt = participationservice.joinRoom(user, room);
        }
    	
//        System.out.println(roomNum);
//        System.out.println(user);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @GetMapping("/addHashTag")
    //hashtag num을 넘겨주면 해당 번호에 맞는 hashtag를 찾아옴
    //넘겨받은 hashtag를 room의 hashtag set에 저장
    public ResponseEntity<?> addHashtag(@RequestParam int roomNum, @RequestParam int hashtagNum) throws SQLException {
    	Hashtag hashtag = hashtagService.getHashtag(hashtagNum);
    	System.out.println("추가하려는 hashtag의 정보는 " + hashtag);
    	Room room = roomservice.getRoom(roomNum);
    	
    	System.out.println("room의 정보는 " + room);

    	RoomHashtag roomHt = new RoomHashtag(room, hashtag);
    	System.out.println(roomHt);
    	
    	int cnt = roomHashtagService.addRoomHashtag(roomHt);
//    	int cnt = 1;
		if(cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

//		if(room.getRoomHashtag().add(roomHt)) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	    else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping("/getHashtag")
    public ResponseEntity<?> getHashtagList(@RequestParam int roomNum) throws SQLException {
    	System.out.println("들어옴");
//    	Room room = roomservice.getRoom(roomNum);
    	
    	List<RoomHashtag> set = roomHashtagService.getListRoomHashtag();
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

}
