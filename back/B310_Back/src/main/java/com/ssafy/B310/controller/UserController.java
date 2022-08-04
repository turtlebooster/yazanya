package com.ssafy.B310.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.B310.entity.*;
import com.ssafy.B310.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.B310.annotation.NoJwt;
import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.entity.UserHashtag;
import com.ssafy.B310.service.HashtagService;
import com.ssafy.B310.service.JwtService;
import com.ssafy.B310.service.UserHashtagService;
import com.ssafy.B310.service.UserService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserHashtagService userHashtagService;
	
	@Autowired
	HashtagService hashtagService;
	
	@Autowired
	ProfileService profileService;

	@Autowired
	FollowService followService;

	// 로그인 요청 처리 - POST /user/login
	@NoJwt
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.login(user);
			if (loginUser != null) {
				String token = jwtService.create("userId", loginUser.getUserId(), "access-token");
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}

	// 유저리스트 조회 - GET
	@GetMapping
	public ResponseEntity<List<User>> selectUserList() throws Exception {
		return new ResponseEntity<List<User>>(userService.selectUserList(), HttpStatus.OK);
	}
	
	// 수정하기
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user) throws SQLException {
		int cnt = userService.updateUser(user);
		
		// 상태 코드만으로 구분
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// ID 중복체크
	@NoJwt
	@GetMapping("/checkId")
	public ResponseEntity<?> checkId(@RequestParam String userId) throws SQLException {
		// User 회원가입
		int cnt = userService.checkId(userId);
		
		// 상태 코드만으로 구분
		if(cnt!=0) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); //중복된 것이므로 사용 불가능
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 닉네임 중복체크
	@NoJwt
	@GetMapping("/checkNick")
	public ResponseEntity<?> checkNickname(@RequestParam String userNickname) throws SQLException {
		// User 회원가입
		int cnt = userService.checkNickname(userNickname);
		
		// 상태 코드만으로 구분
		if(cnt!=0) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); //중복된 것이므로 사용 불가능
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 회원가입
	@NoJwt
	@PostMapping("/regist")
	public ResponseEntity<?> registUser(@RequestBody User user) throws SQLException {
		int cnt = userService.registUser(user);
		
		// 상태 코드만으로 구분
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 탈퇴 - Patch
	@PatchMapping("/quit/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) throws SQLException {
		System.out.println(userId);
		User user = new User();
		user.setUserId(userId);
		int cnt = userService.deleteUser(user);
		
		//상태 코드만으로 구분
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}	 
	
	// 관리자 회원정보 삭제 - DELETE
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> adminDeleteUser(@PathVariable("userId") String userId) throws SQLException{
		int cnt = userService.adminDeleteUser(userId);
		
		//상태 코드만으로 구분
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 아이디 찾기
	@NoJwt
	@GetMapping("/findid")
	public ResponseEntity<?> findId(@RequestParam String userEmail) throws SQLException {
		String userId = userService.findId(userEmail);
		
		if(userId != null) { //해당 이메일로 가입한 유저가 있음
			return new ResponseEntity<String>(userId, HttpStatus.OK);
		} else { //해당 이메일로 가입한 유저가 없음
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 비밀번호 찾기 -> 이메일로 임시 비밀번호 전송
	@NoJwt
	@GetMapping("/findpw")
	public ResponseEntity<?> getTmpPw(@RequestParam String userId, @RequestParam String userEmail) throws SQLException {
		
		int result = userService.checkId(userId);
		
		if(result == 0) { //존재하지 않는 유저
			return new ResponseEntity<String>("해당 ID로 가입된 유저가 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		} else { //존재하는 유저
			User user = userService.myPage(userId);
			
			if(userEmail.equals(user.getUserEmail())) {
				userService.makeTmpPw(userId);
				return new ResponseEntity<String>("임시 비밀번호를 전송했습니다. 이메일을 확인해주세요.", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("가입 Email과 일치하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
	}
	
	
	//유저 해쉬태그 추가
	@PostMapping("/hashtag")
	public ResponseEntity<?> addUserHashtag(@RequestParam String userId, @RequestParam int hashtagNum) throws SQLException {
		User user = userService.myPage(userId);
		Hashtag hashtag = hashtagService.getHashtag(hashtagNum);
		
		UserHashtag userHashtag = new UserHashtag(user, hashtag);

		int cnt = userHashtagService.addUserHashtag(userHashtag);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//유저 해쉬태그 삭제
	@DeleteMapping("/hashtag")
	public ResponseEntity<?> deleteUserHashtag(@RequestParam String userId, @RequestParam int hashtagNum) throws SQLException {
		User user = userService.myPage(userId);
		Hashtag hashtag = hashtagService.getHashtag(hashtagNum);
		
		int cnt = userHashtagService.deleteUserHashtag(user, hashtag);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//유저 해쉬태그 목록
	@GetMapping("/hashtag")
	public ResponseEntity<?> getUserHashtagList(@RequestParam String userId) throws SQLException {
		User user = userService.myPage(userId);
		
		List<UserHashtag> list = userHashtagService.getUserHashtag(user);
		
		if(list.isEmpty()) {
			System.out.println("해당 user는 해쉬태그가 없음");
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		for(UserHashtag uht : list) {
			System.out.println(uht.getHashtag().getHashtagName());
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 유저 프로필 페이지
	// 프로필 수정
	@PutMapping("/profile/{userId}")
	public ResponseEntity<?> updateProfile (HttpServletRequest request, @RequestBody User user, @PathVariable("userId") String userId) throws SQLException {
		String requestUserId = jwtService.getUserID(request.getHeader("access-token"));
		if (requestUserId.equals(userId)) {
			int cnt = profileService.updateProfile(userId, user);

			if (cnt == 1) {
				System.out.println(user);
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}

			else {
				return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);}
		}
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 프로필 정보 가져오기
	@GetMapping("/profile/{userId}")
	public ResponseEntity<?> getProfile (@PathVariable("userId") String userId) throws SQLException {

		User userProfile = profileService.getProfile(userId);

		return new ResponseEntity<User>(userProfile, HttpStatus.OK);
	}

	// 프로필 이미지 추가
	@PostMapping("/profile/{userId}/profileImg")
	public ResponseEntity<?> addProfileImage(@PathVariable("userId") String userId, @RequestPart MultipartFile pic) throws IOException, SQLException {
		UUID uuid = UUID.randomUUID();
		String profileImg = uuid.toString();

		String path = "C:/Users/multicampus/Desktop/yazanya/S07P12B310/back/B310_Back/src/main/resources/static/userImg/";

		Path imagePath = Paths.get(path + profileImg+'_'+userId);

		Path p = Files.write(imagePath, pic.getBytes());

//		try {
//			Files.write(imagePath, pic.getBy	tes());
//		} catch (Exception e) {
//
//		}
		profileService.updateProfileImg(userId, imagePath.toString());

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

	}
	
	// 팔로우
	// 팔로우 추가
	@PostMapping("/profile/{userId}")
	public ResponseEntity<?> userFollow (HttpServletRequest request, @PathVariable("userId") String followToUserId) throws SQLException {
		String followFromUserId = jwtService.getUserID(request.getHeader("access-token"));

		Follow follow = followService.follow(followToUserId, followFromUserId);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 팔로우 취소
	@DeleteMapping("/profile/{userId}")
	public ResponseEntity<?> userUnFollow (HttpServletRequest request, @PathVariable("userId") String followToUserId) throws SQLException {
		String followFromUserId = jwtService.getUserID(request.getHeader("access-token"));

		followService.deleteByFollowToUserAndFollowFromUser(followToUserId, followFromUserId);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 유저 팔로우 목록
//	@GetMapping("/profile/{userId}/follow")
//	public ResponseEntity<?> followList (@PathVariable("userId") String userId) throws SQLException {
//
//		return new ResponseEntity<List<User>>(followService.followList(userId), HttpStatus.OK);
//	}
}

