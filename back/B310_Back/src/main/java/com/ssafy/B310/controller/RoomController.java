package com.ssafy.B310.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import io.swagger.models.auth.In;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.B310.annotation.NoJwt;
import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;
import com.ssafy.B310.entity.RoomForcedExit;
import com.ssafy.B310.entity.RoomHashtag;
import com.ssafy.B310.entity.RoomThumbnail;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.jwt.JwtTokenProvider;
import com.ssafy.B310.repository.RoomForceExitRepository;
import com.ssafy.B310.service.HashtagService;
import com.ssafy.B310.service.ParticipationHistoryService;
import com.ssafy.B310.service.ParticipationService;
import com.ssafy.B310.service.RoomHashtagService;
import com.ssafy.B310.service.RoomService;
import com.ssafy.B310.service.ThumbnailService;
import com.ssafy.B310.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
    JwtTokenProvider jwtService;
    
    @Autowired
    ThumbnailService thumbnailService;

	@Autowired
	RoomForceExitRepository roomForceExitRepository;
	
	@Value("${thumbnailImg.path}")
    String thumbnailImgPath;

    @PostMapping
    @ApiOperation(value = "방 생성", 
	    		  notes = "방을 하나 생성함\r\n" + 
	    		  		"로그인한 유저를 이 방의 관리자로 지정\r\n" + 
	    		  		"{\r\n" + 
	    		  		"  \"roomName\" : (방 이름),\r\n" + 
	    		  		"  \"roomDescription\" : (방 설명),\r\n" + 
	    		  		"  \"roomCapacity\" : (인원 제한),\r\n" + 
	    		  		"  \"roomSound\" : (전체 소리 true/false),\r\n" + 
	    		  		"  \"roomVideo\" : (전체 비디오 true/false),\r\n" + 
	    		  		"  \"roomStudyTime\" : (공부시간 알람 간격),\r\n" + 
	    		  		"  \"roomRestTime\" : (휴식시간 알람 간격),\r\n" + 
	    		  		"  \"roomHasPw\" : (방 비밀번호 유무 true/false),\r\n" +
	    		  		"  \"roomPw\" : (방 비밀번호),\r\n" + 
	    		  		"  \"roomActive\" : (방 활성화 여부 true/false)\r\n" + 
	    		  		" }")
    public ResponseEntity<?> createRoom(@RequestBody Room room, HttpServletRequest request) throws SQLException{
    	int userNum = jwtService.getUserNum(request.getHeader("access-token"));
        int cnt = 0;
        if (userNum != -1) {
        	cnt = roomservice.createRoom(room, userNum);        	
//        	if (hashtagService.addHashtagList(roomInfo.getRoomHash(), cnt) == 0) {
//        		return new ResponseEntity<String>("failToAddHashtag", HttpStatus.OK);
//        	}
        }

        if(cnt!=0) return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @PostMapping("/filter")
    @ApiOperation(value = "방 검색", 
    			  notes = "주어진 조건으로 방을 검색하여 목록 전달\r\n" + 
    			  		"{\r\n" + 
    			  		"  \"video\" : 0(끔) / 1(켬) / 2(무관),\r\n" + 
    			  		"  \"sound\" : 0(끔) / 1(켬) / 2(무관),\r\n" + 
    			  		"  \"fullcheck\" : 0(무관) / 1 (정원이 안찬 방)\r\n" + 
    			  		"}")
    public ResponseEntity<?> filterRoom(@RequestBody Map<String, Integer> params) throws SQLException{
    	List<Room> roomList = roomservice.filterRoom(params);
    	
    	if(!roomList.isEmpty())
    		return new ResponseEntity<List<Room>>(roomList, HttpStatus.OK);
    	else
    		return new ResponseEntity<String>(FAIL, HttpStatus.OK); 
    }
    
    @GetMapping("/{roomNum}")
    @ApiOperation(value = "방 정보 불러오기",
    			  notes = "전달된 방 번호에 해당하는 방의 정보 전달")
    public ResponseEntity<?> getRoom(@PathVariable int roomNum) throws SQLException{
    	Room room = roomservice.getRoom(roomNum);
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	String tn = room.getRoomThumbnail();
    	
    	if (thumbnailService.getThumbnail(tn) != null) {
    		String thumbnailPath = thumbnailService.getThumbnail(tn).getThumnailPath();
    		resultMap.put("thumnailPath", thumbnailPath);    		
    	}
    			
		resultMap.put("room", room);
		resultMap.put("message", SUCCESS);
    	
    	if(room != null) {
    		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    	}
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @DeleteMapping("/{roomNum}")
    @ApiOperation(value = "방 삭제", 
    			  notes = "전달된 방 번호에 해당하는 방을 삭제")	
    public ResponseEntity<?> removeRoom(HttpServletRequest request, @PathVariable int roomNum) throws SQLException{
		int userNum = jwtService.getUserNum(request.getHeader("access-token"));
        int cnt = 0;
        if (userNum != -1) {
        	cnt = roomservice.removeRoom(roomNum, userNum);
        }
		
        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "방 정보 수정",
	  			  notes = "전달된 방 번호에 해당하는 방의 정보 수정\r\n" + 
	  			  		"{\r\n" +  
	  			  		"  \"roomNum\" : (방 번호)," +
	  			  		"  \"roomName\" : (방 이름),\r\n" + 
	  			  		"  \"roomDescription\" : (방 설명),\r\n" + 
	  			  		"  \"roomCapacity\" : (인원 제한),\r\n" + 
	  			  		"  \"roomSound\" : (전체 소리 true/false),\r\n" + 
	  			  		"  \"roomVideo\" : (전체 비디오 true/false),\r\n" + 
	  			  		"  \"roomStudyTime\" : (공부시간 알람 간격),\r\n" + 
	  			  		"  \"roomRestTime\" : (휴식시간 알람 간격),\r\n" + 
	  			  		"  \"roomPw\" : (방 비밀번호),\r\n" + 
	  			  		"  \"roomActive\" : (방 활성화 여부 true/false)\r\n" + 
	  			  		" }")
    public ResponseEntity<?> updateRoom(@RequestBody Room room) throws SQLException{

        int cnt = roomservice.updateRoom(room);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    @GetMapping("/active")
    @ApiOperation(value = "방 활성화 여부 체크", 
    			  notes = "방에 입장할 수 있다면 success\n 인원이 다 차서or방장이 방을 잠궈서 입장할 수 없다면 fail")	
    @ApiImplicitParam(name = "roomNum", value = "방 번호", dataType = "int")
    public ResponseEntity<?> enableJoinRoom(@RequestParam int roomNum) throws SQLException {
    	boolean result = roomservice.enableJoinRoom(roomNum);
    	
    	if(result) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    @Transactional
    @PostMapping("/{roomNum}")
    @ApiOperation(value = "방 입장", 
    			  notes = "방 자리가 남아있고, 강퇴 유저가 아니며, 방 비밀번호가 일치하거나 비밀번호가 없는 경우 success\n" +
    					  "방 비밀번호가 다를 경우 failToPw \n" +
						  "방이 풀방일 경우 failToFullRoom \n" +
						  "강퇴 당한 유저일 경우 failToForcedExitUser \n" +
						  "이미 다른 방에 입장해 있는 유저일 경우 alreadyParticipateUser\n" +

    					  "{\r\n" + 
    					  "  	roomPw : (방 비밀번호 / 없을경우(입력x))\r\n" +
    					  "}")	
    public ResponseEntity<?> joinRoom(@RequestBody Room room, @PathVariable int roomNum, HttpServletRequest request) throws SQLException {
		String userId = jwtService.getUserID(request.getHeader("access-token"));
		Room r = roomservice.getRoom(roomNum);
		int statusCode = 0;
		int cnt = 0;
		List<RoomForcedExit> fList = roomForceExitRepository.findByRoom_roomName(r.getRoomName());
		if (r.isRoomHasPw()) {
			if (!BCrypt.checkpw(room.getRoomPw(), r.getRoomPw())) {
				statusCode = 2;
			}
		}
		if (!(roomservice.enableJoinRoom(roomNum))) {
			System.out.println(!(roomservice.enableJoinRoom(roomNum)));
			statusCode = 3;
		}
		for (RoomForcedExit f : fList) {
			if (f.getUserId().equals(userId)) {
				statusCode = 1;
			}
		}
		switch (statusCode) {
			case 1:
				return new ResponseEntity<String>("failToForcedExitUser", HttpStatus.OK);
			case 3:
				return new ResponseEntity<String>("failToFullRoom", HttpStatus.OK);
			case 2:
				return new ResponseEntity<String>("failToPw", HttpStatus.OK);
			default:
				break;
		}
		cnt = participationservice.joinRoom(userId, r);
		if (cnt == 1) {
			roomservice.addParticipation(r);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>("alreadyParticipateUser", HttpStatus.OK);
	}


    @PostMapping("/hashtag")
    @ApiOperation(value = "방에 대한 해쉬태그 정보 추가", 
				    notes = "[\r\n" + 
				    		"    \"해시태그1\",\r\n" + 
				    		"    \"해시태그2\",\r\n" + 
				    		"    \"해시태그3\"\r\n" + 
				    		"]")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "roomNum", value = "방 번호", dataType = "int"),
    })
    public ResponseEntity<?> addHashtag(@RequestParam int roomNum, @RequestBody List<String> hashtagNameList) throws SQLException {
    	int cnt = hashtagService.addHashtagList(hashtagNameList, roomNum);
		if(cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	    else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    @GetMapping("/hashtag")
    @ApiOperation(value = "방에 설정된 해쉬태그 목록 조회", notes = "방에 설정된 해쉬태그 목록 조회")
   	@ApiImplicitParam(name = "roomNum", value = "방 번호", dataType = "int")
    public ResponseEntity<?> getHashtagList(@RequestParam int roomNum) throws SQLException {
    	Room room = roomservice.getRoom(roomNum);
    	
    	List<RoomHashtag> roomHashtagList = roomHashtagService.getRoomHashtagBy(room);
    	List<Hashtag> list = new ArrayList<Hashtag>();
    	for (RoomHashtag rht : roomHashtagList) {
    		list.add(rht.getHashtag());
    	}
    	    	
    	return new ResponseEntity<List<Hashtag>>(list, HttpStatus.OK);
    }
    
    @DeleteMapping("/hashtag")
    @ApiOperation(value = "방에서 해쉬태그 삭제", notes = "방에 해당하는 해쉬태그 삭제")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "roomNum", value = "방 번호", dataType = "int"),
    	@ApiImplicitParam(name = "hashtagNum", value = "해쉬태그 번호", dataType = "int")
    })
    public ResponseEntity<?> deleteHashtag(@RequestParam int roomNum, @RequestParam int hashtagNum) throws SQLException {
    	Room room = roomservice.getRoom(roomNum);
    	Hashtag hashtag = hashtagService.getHashtag(hashtagNum);
    	
    	int cnt = roomHashtagService.deleteRoomHashtag(room, hashtag);
    	
		if(cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	    else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @GetMapping("/recommend")
    @ApiOperation(value = "추천 방 리스트", 
    			  notes = "해쉬태그 번호들을 받아 관련된 방 목록 전달\r\n"
    				+ "hashtagNum=1,2,3,4,5")
    @ApiImplicitParam(name = "hashtagNum", value = "해쉬태그 번호")
    public ResponseEntity<?> recommendRoom(@RequestParam(value="hashtagNum", required=false, defaultValue="") List<Integer> hashtagNumList) {
    	return new ResponseEntity<Map<String, Object>>(roomservice.getRecommendHashtagList(hashtagNumList), HttpStatus.OK);
    }

	@GetMapping("/search")
	@ApiOperation(value = "추천 방 리스트",
			notes = "해쉬태그 번호들을 받아 관련된 방 목록 전달\r\n"
					+ "hashtagNum=1,2,3,4,5")
	@ApiImplicitParam(name = "hashtagNum", value = "해쉬태그 번호")
	public ResponseEntity<?> searchRoom(@RequestParam(value="hashtagName", required=false, defaultValue="") List<String> hashtagNameList) {
		return new ResponseEntity<Map<String, Object>>(roomservice.searchRecommendHashtagList(hashtagNameList), HttpStatus.OK);
	}
    
    @PatchMapping("/exit/{roomNum}")
    @ApiOperation(value = "유저 퇴장", 
    			  notes = "해당 방의 유저 목록에서 유저 제거\r\n" + 
    			  		"{\r\n" + 
    			  		"  userId : (유저 아이디)\r\n" + 
    			  		"}")
    public ResponseEntity<?> exitRoom(@RequestBody Map<String, String> params , @PathVariable int roomNum ) throws SQLException {
        String userId = params.get("userId");
        int cnt = participationservice.exitRoom(userId, roomNum);

        Room room = roomservice.getRoom(roomNum);
        if(cnt==1)  {
        	roomservice.decreaseParticipation(room);
        	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    @NoJwt
    @PostMapping("/exit/{roomNum}/{userId}")
    @ApiOperation(value = "유저 퇴장")
    public ResponseEntity<?> abnormalExitRoom(@PathVariable int roomNum, @PathVariable String userId) throws SQLException {
        int cnt = participationservice.exitRoom(userId, roomNum);

        Room room = roomservice.getRoom(roomNum);
        if(cnt==1)  {
        	roomservice.decreaseParticipation(room);
        	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @GetMapping("/join/{roomNum}")
    @ApiOperation(value = "방에 접속한 유저 목록",
    			  notes = "방에 접속한 유저 목록 전달")
    public ResponseEntity<?> joinedUser(@PathVariable int roomNum) throws SQLException {
        List<User> joinedUserList = participationservice.joinedUser(roomNum);

        return new ResponseEntity<List<User>>(joinedUserList, HttpStatus.OK);
    }

//	@Transactional
    @GetMapping("/history")
    @ApiOperation(value = "이전에 방문했던 방",
    			  notes = "이전에 방문했던 방 목록 전달")
    public ResponseEntity<?> getRoomHistory(HttpServletRequest request) throws SQLException {
        String userId = jwtService.getUserID(request.getHeader("access-token"));
        return new ResponseEntity<>(participationHistoryService.getRoomHistoryList(userId), HttpStatus.OK);
    }

    @PostMapping("/Thumbnail/{roomNum}")
    @ApiOperation(value = "방 썸네일 추가", notes = "방 썸네일 이미지 저장")
    public ResponseEntity<?> addThumbnail(@PathVariable int roomNum, @RequestPart MultipartFile thumbnail) throws Exception {
    	System.out.println("들어왔니??");
    	UUID uuid = UUID.randomUUID();
    	String thumbnailImg = uuid.toString();
    	
    	String path = thumbnailImgPath;
    	
    	File makeFolder = new File(path);
    	
    	if(!makeFolder.exists()) {
    		makeFolder.mkdir(); 
    		System.out.println(path + "에 폴더 생성");
    		System.out.println(("폴더가 존재하는지 체크 true/false : "+ makeFolder.exists()));
    	} else {
    		System.out.println("폴더 이미 존재함");
    	}
    	String imageName = thumbnailImg + "." + thumbnail.getContentType().split("/")[1];
    	Path imagePath = Paths.get(path + imageName);
    	
    	Files.write(imagePath, thumbnail.getBytes());
    	
    	RoomThumbnail tn = new RoomThumbnail();
    	tn.setThumbnailId(thumbnailImg);
    	tn.setThumbnailName(thumbnail.getOriginalFilename());
    	tn.setThumnailPath(imageName);
    	
    	thumbnailService.saveFile(tn);
    	
    	long result = roomservice.addThumbnail(roomNum, imageName);
    	
    	if(result != 0) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);

    }
    
    @GetMapping("/hasPw/{roomNum}")
    @ApiOperation(value = "방이 비밀번호 유무", notes = "방 번호에 해당하는 방이 비밀번호를 가졌는지 여부 전달\r\n 비밀번호 있을경우 true \r\n 비밀번호 없을경우 false \r\n 오류 fail")
    public ResponseEntity<?> getRoomHistory(@PathVariable int roomNum) throws SQLException {
    	int cnt = roomservice.hasPw(roomNum);
    	
    	if (cnt == 1) return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    	else if (cnt == 2) return new ResponseEntity<Boolean>(false, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        
    }


	@ApiOperation(value = "유저 강제퇴장",
			notes = "해당 방의 유저 목록에서 유저 제거 및 강제퇴장목록에 기록\r\n" +
					"{\r\n" +
					"  userId : (유저 아이디)\r\n" +
					" roomNum: (방 번호) (문자열로 보내야함)\r\n" +
					"}")
	@PostMapping("/forceExit")
	public ResponseEntity<?> forceExit(HttpServletRequest request, @RequestBody Map<String, String> params) throws SQLException {
		String userNickname = params.get("userNickname");
		int roomNum = Integer.parseInt(params.get("roomNum"));
		String reqUserId = jwtService.getUserID(request.getHeader("access-token"));
		
		
		int cnt = roomservice.forcedExitUser(reqUserId, userNickname, roomNum);
		if (cnt == 1) {return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);}
		else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
    
	@GetMapping("/searchByName/{search}")
    @ApiOperation(value = "방 검색", notes = "특정 단어를 포함하는 방 리스트 전달")
    public ResponseEntity<?> searchRoom(@PathVariable String search) throws Exception {
		System.out.println("여기?");
    	Map<String ,Object> roomList = roomservice.searchRoomByName(search);
    	
    	if(!roomList.isEmpty()) {
    		
    		return new ResponseEntity<Map<String ,Object>>(roomList, HttpStatus.OK);
    	}
    	else
    		System.out.println("비었나?");
    		return new ResponseEntity<String>(FAIL, HttpStatus.OK); 
    }

}
