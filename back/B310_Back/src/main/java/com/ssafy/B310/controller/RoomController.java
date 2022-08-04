package com.ssafy.B310.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.entity.RoomThumbnail;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.service.HashtagService;
import com.ssafy.B310.service.ParticipationService;
import com.ssafy.B310.service.RoomHashtagService;
import com.ssafy.B310.service.RoomService;
import com.ssafy.B310.service.ThumbnailService;

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
    
    @Autowired
    ThumbnailService thumbnailService;

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
    	
    	String thumbnailPath = thumbnailService.getThumbnail(room.getRoomThumbnail()).getThumnailPath();
    	
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("room", room);
		resultMap.put("message", SUCCESS);
		resultMap.put("thumnailPath", thumbnailPath);
    	
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
    @PostMapping("/{roomNum}/{userId}")
    public ResponseEntity<?> joinRoom(@RequestBody Map<String, Integer> pw , @PathVariable("roomNum") int roomNum, @PathVariable("userId") String userId) throws SQLException {
    	Room room = roomservice.getRoom(roomNum);
    	
        int roomPw = pw.get("roomPw");
        
        int cnt = 0;
        if((room.getRoomPw() == roomPw) && roomservice.enableJoinRoom(roomNum)) {
        	cnt = participationservice.joinRoom(userId, room);
        }

        if(cnt==1) {
        	roomservice.addParticipation(room);
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

        return new ResponseEntity<List<User>>(joinedUserList, HttpStatus.OK);

    }
    
    //방 썸네일 추가
    @PostMapping("/addThumbnail/{roomNum}")
    public ResponseEntity<?> addThumbnail(@PathVariable int roomNum, @RequestPart MultipartFile thumbnail) throws Exception {
    	
    	UUID uuid = UUID.randomUUID();
    	String fileId = uuid.toString();
    	
    	String path = "C:/image/";
    	
    	File makeFolder = new File(path);
    	
    	if(!makeFolder.exists()) {
    		makeFolder.mkdir(); 
    		System.out.println(path + "에 폴더 생성");
    		System.out.println(("폴더가 존재하는지 체크 true/false : "+ makeFolder.exists()));
    	} else {
    		System.out.println("폴더 이미 존재함");
    	}
    	
    	Path imagePath = Paths.get(path + fileId);
    	
    	Path p = Files.write(imagePath, thumbnail.getBytes());
    	
    	RoomThumbnail tn = new RoomThumbnail();
    	tn.setThumbnailId(fileId);
    	tn.setThumbnailName(thumbnail.getOriginalFilename());
    	tn.setThumnailPath(imagePath.toString());
    	
    	thumbnailService.saveFile(tn);
    	
//    	여기서 db에 파일 경로를 저장한다. post entity
//    	게시글에 사진을 포함시킨다고 하면
//    	post에 이미지 경로 필드 하나 더 해주면 되는 것이다.
//    	그럼 jsp에서 image에 src에 post엔티티에 있는 이미지 경로를 저장하면 되는 것이다.
//    	그렇다면 path를 db에 저장했을 시에 서버를 이전한다고 한다면 경로가 다 틀어질 수 밖에 없다
    	
//    	그렇기 때문에 그냥 경로는 서버가 들고 db에는 경로가 저장돼선 안된다.
//    	그래서 위 상황에서 디비에 저장한다고 치면 fileName를 저장하는 것이다.
    	
//    	근데 이미지 파일 이름을 저장하게 하면 파일 이름이 겹칠 수 있다
//    	그래서 UUID uuid = UUID.randomUUID();를 사용하면 중복되면 난수를 발생시킨다.
    	long result = roomservice.addThumbnail(roomNum, fileId);
    	
    	if(result != 0) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
}
