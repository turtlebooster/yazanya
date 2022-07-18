package com.ssafy.B310.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.B310.entity.User;
import com.ssafy.B310.service.JwtService;
import com.ssafy.B310.service.UserService;


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

	// 로그인 요청 처리 - POST /user/login
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
	
    // 상세 조회 - GET /user
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userId") String userId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				User user = userService.myPage(userId);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
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
	@PostMapping("/{userId}")
	public ResponseEntity<?> checkId(@PathVariable String userId) throws SQLException {
		// User 회원가입
		int cnt = userService.checkId(userId);
		System.out.println("cnt는 " + cnt);
		// 상태 코드만으로 구분
		if(cnt!=0) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); //중복된 것이므로 사용 불가능
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 회원가입
	@PostMapping("/regist")
	public ResponseEntity<?> registUser(@RequestBody User user) throws SQLException {
		// User 회원가입
		int cnt = userService.registUser(user);
		
		// 상태 코드만으로 구분
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 탈퇴 - Patch
	@PatchMapping("/quit/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) throws SQLException {
		System.out.println(1111);
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
	@GetMapping("/findid")
	public ResponseEntity<?> findId(@RequestParam String userEmail) throws SQLException {
		return new ResponseEntity<String>(userService.findId(userEmail), HttpStatus.OK);
	}
	
	// 비밀번호 찾기
	@GetMapping("/findpw")
	public ResponseEntity<?> getInfo(@RequestParam String userId, @RequestParam String userEmail) throws SQLException {
		
		User user = new User();
		user.setUserEmail(userEmail);
		user.setUserId(userId);
		
		return new ResponseEntity<String>(userService.findPw(user), HttpStatus.OK);
	}
	
}
