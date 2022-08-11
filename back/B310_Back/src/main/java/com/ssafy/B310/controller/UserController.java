package com.ssafy.B310.controller;

import java.io.File;
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

import com.ssafy.B310.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.B310.annotation.NoJwt;
import com.ssafy.B310.dto.TokenResponse;
import com.ssafy.B310.entity.Auth;
import com.ssafy.B310.entity.Follow;
import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.entity.UserHashtag;
import com.ssafy.B310.jwt.JwtTokenProvider;
import com.ssafy.B310.repository.AuthRepository;
import com.ssafy.B310.service.FollowService;
import com.ssafy.B310.service.HashtagService;
import com.ssafy.B310.service.ProfileService;
import com.ssafy.B310.service.UserHashtagService;
import com.ssafy.B310.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHashtagService userHashtagService;

    @Autowired
    HashtagService hashtagService;

    @Autowired
    ProfileService profileService;

    @Autowired
    FollowService followService;
    
    @Autowired
    AuthRepository authRepository;
    
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
//    @Value("${profileImg.path}")
    String profileImgPath;
    
    //Access Token 재발급
    @NoJwt
    @PostMapping("/auth")
    public ResponseEntity<?> issueAccessToken(HttpServletRequest request) throws Exception {	
    	TokenResponse response =  jwtTokenProvider.issueAccessToken(request);
    	
    	//Refresh Token은 유효해서 새로 Access Token을 발급한 경우
    	if(response != null) {
    		return new ResponseEntity<String>(response.getACCESS_TOKEN(), HttpStatus.OK);   
    	}
    	//Refresh Token도 유통기한 지났음
    	return new ResponseEntity<String>(FAIL, HttpStatus.OK);   	
    }

    // 로그인 요청 처리 - POST /user/login
    @NoJwt
    @PostMapping("/login")
//    @ApiOperation(value = "로그인", notes = "{\\n\\\"userId\\\" : {String}, \\n \\\"userPw\\\": {String} \n}")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
    	    	
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String id = user.getUserId();
        try {
            String result = userService.login(user);
            if (result.equals(id)) {
                User loginUser = userRepository.findByUserId(result).get();
                String accessToken = jwtTokenProvider.createAccessToken(user.getUserId());
                String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());
                
                Auth auth = Auth.builder()
            			.user(loginUser)
            			.refreshToken(refreshToken)
            			.build();
                
                if(authRepository.findByuser_userId(user.getUserId()).isPresent()) {
                	Auth alreadyAuth = authRepository.findByuser_userId(user.getUserId()).get();
                	authRepository.delete(alreadyAuth);
                }
            	authRepository.save(auth);
    
                resultMap.put("token", TokenResponse.builder()
                		.ACCESS_TOKEN(accessToken)
                		.REFRESH_TOKEN(refreshToken)
                		.build());
                
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else if (result.equals("pwErr")){
                resultMap.put("message", "pwErr");
                status = HttpStatus.ACCEPTED;
            } else if (result.equals("noId")) {
                resultMap.put("message", "noId");
                status = HttpStatus.ACCEPTED;

            }
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
    // 유저리스트 조회 - GET
    @GetMapping
//    @ApiOperation(value = "유저 리스트 조회")
    public ResponseEntity<List<User>> selectUserList() throws Exception {
        return new ResponseEntity<List<User>>(userService.selectUserList(), HttpStatus.OK);
    }

    // 수정하기
    @PutMapping("/update")
    @ApiOperation(value = "유저 정보 수정", notes = "{\n\"userPw\" : {String: 유저비밀번호}, \n \"userNickname\": {String: 유저닉네임} \n} \n을 수정한다.")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws SQLException {
        int cnt = userService.updateUser(user);

        // 상태 코드만으로 구분
        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // ID 중복체크
    @NoJwt
    @GetMapping("/checkId")
    @ApiOperation(value = "로그인 시 id 중복을 체크한다.")
    public ResponseEntity<?> checkId(@RequestParam String userId) throws SQLException {
        // User 회원가입
        int cnt = userService.checkId(userId);

        // 상태 코드만으로 구분
        if (cnt != 0) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); //중복된 것이므로 사용 불가능
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // 닉네임 중복체크
    @NoJwt
    @GetMapping("/checkNick")
    @ApiOperation(value = "닉네임 중복 체크", notes = "회원가입 시 닉네임 중복을 체크한다.")
    public ResponseEntity<?> checkNickname(@RequestParam String userNickname) throws SQLException {
        // User 회원가입
        int cnt = userService.checkNickname(userNickname);

        // 상태 코드만으로 구분
        if (cnt != 0) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK); //중복된 것이므로 사용 불가능
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // 회원가입
    @NoJwt
    @PostMapping("/regist")
    @ApiOperation(value = "회원가입", notes = "{\n \"userId\": {String:유저아이디},\n \"userPw\":{String:유저비밀번호},\n \"userEmail\":{String:유저이메일},\n \"userName\":{String:유저이름},\n \"userNickName\":{String:유저닉네임}\n}")
    public ResponseEntity<?> registUser(@RequestBody User user) throws SQLException {    	
        int cnt = userService.registUser(user);
        
        // 상태 코드만으로 구분
        if (cnt == 1) 
        	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // 탈퇴 - Patch
    @PatchMapping("/quit/{userId}")
    @ApiOperation(value = "회원 탈퇴")
    @ApiImplicitParam(name = "userId", paramType = "path")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) throws SQLException {
        System.out.println(userId);
        User user = new User();
        user.setUserId(userId);
        int cnt = userService.deleteUser(user);

        //상태 코드만으로 구분
        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // 관리자 회원정보 삭제 - DELETE
    @DeleteMapping("/{userId}")
    @ApiOperation(value = "관리자 회원정보 삭제")
    @ApiImplicitParam(name = "userId", paramType = "path")
    public ResponseEntity<?> adminDeleteUser(@PathVariable("userId") String userId) throws SQLException {
        int cnt = userService.adminDeleteUser(userId);

        //상태 코드만으로 구분
        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // 아이디 찾기
    @NoJwt
    @GetMapping("/findid")
    @ApiOperation(value = "아이디 찾기")
    @ApiImplicitParam(name = "userEmail", paramType = "query")
    public ResponseEntity<?> findId(@RequestParam String userEmail) throws SQLException {
        String userId = userService.findId(userEmail);

        if (userId != null) { //해당 이메일로 가입한 유저가 있음
            return new ResponseEntity<String>(userId, HttpStatus.OK);
        } else { //해당 이메일로 가입한 유저가 없음
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    // 비밀번호 찾기 -> 이메일로 임시 비밀번호 전송
    @NoJwt
    @GetMapping("/findpw")
    @ApiOperation(value = "비밀번호 찾기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId"),
            @ApiImplicitParam(name = "userEmail")
    })
    public ResponseEntity<?> getTmpPw(@RequestParam String userId, @RequestParam String userEmail) throws SQLException {

        int result = userService.checkId(userId);

        if (result == 0) { //존재하지 않는 유저
            return new ResponseEntity<String>("해당 ID로 가입된 유저가 없습니다.", HttpStatus.OK);
        } else { //존재하는 유저
            User user = userService.myPage(userId);

            if (userEmail.equals(user.getUserEmail())) {
                userService.makeTmpPw(userId);
                return new ResponseEntity<String>("임시 비밀번호를 전송했습니다. 이메일을 확인해주세요.", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("가입 Email과 일치하지 않습니다.", HttpStatus.OK);
            }

        }
    }


    //유저 해쉬태그 추가
    @PostMapping("/hashtag")
    @ApiOperation(value = "해쉬태그 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "유저아이디"),
            @ApiImplicitParam(name = "hashtagNum", value = "해쉬태그 NUM", dataType = "int")
    })
    public ResponseEntity<?> addUserHashtag(@RequestParam String userId, @RequestParam int hashtagNum) throws SQLException {
        User user = userService.myPage(userId);
        Hashtag hashtag = hashtagService.getHashtag(hashtagNum);

        UserHashtag userHashtag = new UserHashtag(user, hashtag);

        int cnt = userHashtagService.addUserHashtag(userHashtag);

        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    //유저 해쉬태그 삭제
    @DeleteMapping("/hashtag")
    @ApiOperation(value = "해쉬태그 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "유저아이디"),
            @ApiImplicitParam(name = "hashtagNum", value = "해쉬태그 NUM", dataType = "int")
    })
    public ResponseEntity<?> deleteUserHashtag(@RequestParam String userId, @RequestParam int hashtagNum) throws SQLException {
        User user = userService.myPage(userId);
        Hashtag hashtag = hashtagService.getHashtag(hashtagNum);

        int cnt = userHashtagService.deleteUserHashtag(user, hashtag);

        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    //유저 해쉬태그 목록
    @GetMapping("/hashtag")
    @ApiOperation(value = "유저 해쉬태그 목록 조회")
    @ApiImplicitParam(name = "userId", value = "유저 아이디")
    public ResponseEntity<?> getUserHashtagList(@RequestParam String userId) throws SQLException {
        User user = userService.myPage(userId);

        List<UserHashtag> list = userHashtagService.getUserHashtag(user);

        if (list.isEmpty()) {
            System.out.println("해당 user는 해쉬태그가 없음");
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }

        for (UserHashtag uht : list) {
            System.out.println(uht.getHashtag().getHashtagName());
        }

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 유저 프로필 페이지
    // 프로필 수정
    @PutMapping("/profile/{userId}")
    @ApiOperation(value = "프로필 항목을 수정한다.", notes = "\"{\n\\\"profileSelfIntroduce\\\" : {String: 자기소개}\n}")
    public ResponseEntity<?> updateProfile(HttpServletRequest request, @RequestBody User user, @PathVariable("userId") String userId) throws SQLException {
        String requestUserId = jwtTokenProvider.getUserID(request.getHeader("access-token"));
        if (requestUserId.equals(userId)) {
            int cnt = profileService.updateProfile(userId, user);

            if (cnt == 1) {
                System.out.println(user);
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(FAIL, HttpStatus.OK);
            }
        } else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // 프로필 정보 가져오기
    @GetMapping("/profile/{userId}")
    @ApiOperation(value = "프로필을 조회한다.")
    public ResponseEntity<?> getProfile(@PathVariable("userId") String userId) throws SQLException {

        User userProfile = profileService.getProfile(userId);

        return new ResponseEntity<User>(userProfile, HttpStatus.OK);
    }

    // 프로필 이미지 추가
    @PostMapping("/profile/{userId}")
    @ApiOperation(value = "유저 프로필 이미지 추가/수정/삭제", notes = "사진을 보낼 경우 - 프로필 사진 업로드&수정 \n null을 보낼 경우 - 프로필 사진 삭제")
    public ResponseEntity<?> addProfileImage(@PathVariable("userId") String userId, @RequestPart MultipartFile pic) throws IOException, SQLException {
        UUID uuid = UUID.randomUUID();
        String profileImg = uuid.toString();

        String path = profileImgPath;

        File makeFolder = new File(path);

        if (!makeFolder.exists()) {
            makeFolder.mkdir();
            System.out.println(path + "에 폴더 생성");
            System.out.println(("폴더가 존재하는지 체크 true/false : " + makeFolder.exists()));
        } else {
            System.out.println("폴더 이미 존재함");
        }
        
        String imageName = profileImg + '_' + userId + "." + pic.getContentType().split("/")[1];
        Path imagePath = Paths.get(path + imageName);
        
        Files.write(imagePath, pic.getBytes());

        if(!pic.isEmpty()) {
        	profileService.uploadProfileImg(userId, imageName);
        } else {
        	profileService.uploadProfileImg(userId, "profile.png");
        }

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

    }
    
    // 프로필 이미지 이름 불러오기
    @NoJwt
    @GetMapping("/profile/img/{userId}")
    @ApiOperation(value = "유저 프로필 이미지 이름 조회", notes = "유저 Id에 해당하는 이미지 이름을 가져옴\r\n이미지 베이스 URL 뒤에 해당 이미지명을 넣으면 이미지 조회 가능")
    public ResponseEntity<?> showProfileImage(@PathVariable("userId") String userId) throws IOException, SQLException {
        String imageName = profileService.getProfile(userId).getProfilePictureLink();
		return new ResponseEntity<String>(imageName, HttpStatus.OK);
    }
    
    
    // 팔로우
    // 팔로우 추가
    @PostMapping("/follow/{userId}")
    @ApiOperation("팔로우 추가")
    public ResponseEntity<?> userFollow(HttpServletRequest request, @PathVariable("userId") String followToUserId) throws SQLException {
        String followFromUserId = jwtTokenProvider.getUserID(request.getHeader("access-token"));

        Follow follow = followService.follow(followToUserId, followFromUserId);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 팔로우 취소
    @DeleteMapping("/follow/{userId}")
    @ApiOperation(value = "팔로우 취소")
    public ResponseEntity<?> userUnFollow(HttpServletRequest request, @PathVariable("userId") String followToUserId) throws SQLException {
        String followFromUserId = jwtTokenProvider.getUserID(request.getHeader("access-token"));

        followService.deleteByFollowToUserAndFollowFromUser(followToUserId, followFromUserId);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 유저 팔로우 목록
	@GetMapping("/profile/{userId}/follow")
	public ResponseEntity<?> followList (@PathVariable("userId") String userId) throws SQLException {

		return new ResponseEntity<List<User>>(followService.followList(userId), HttpStatus.OK);
	}

    @GetMapping("/profile/{userId}/follower")
    public ResponseEntity<?> followerList (@PathVariable("userId") String userId) throws SQLException {

        return new ResponseEntity<List<User>>(followService.followerList(userId), HttpStatus.OK);
    }
}

