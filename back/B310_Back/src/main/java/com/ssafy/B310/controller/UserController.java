package com.ssafy.B310.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.ssafy.B310.entity.*;
import com.ssafy.B310.repository.EmailConfirmRepository;
import com.ssafy.B310.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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



    @Autowired
    EmailConfirmRepository emailConfirmRepository;

    @Value("${profileImg.path}")
    String profileImgPath;
    
    //Access Token ?????????
    @NoJwt
    @PostMapping("/auth")
    public ResponseEntity<?> issueAccessToken(HttpServletRequest request) throws Exception {	
    	TokenResponse response =  jwtTokenProvider.issueAccessToken(request);
    	
    	//Refresh Token??? ???????????? ?????? Access Token??? ????????? ??????
    	if(response != null) {
    		return new ResponseEntity<String>(response.getACCESS_TOKEN(), HttpStatus.OK);   
    	}
    	//Refresh Token??? ???????????? ?????????
    	return new ResponseEntity<String>(FAIL, HttpStatus.OK);   	
    }

    // ????????? ?????? ?????? - POST /user/login
    @NoJwt
    @PostMapping("/login")
//    @ApiOperation(value = "?????????", notes = "{\\n\\\"userId\\\" : {String}, \\n \\\"userPw\\\": {String} \n}")
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
            logger.error("????????? ?????? : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
    // ??????????????? ?????? - GET
    @GetMapping
//    @ApiOperation(value = "?????? ????????? ??????")
    public ResponseEntity<List<User>> selectUserList() throws Exception {
        return new ResponseEntity<List<User>>(userService.selectUserList(), HttpStatus.OK);
    }

    // ????????????
    @PutMapping("/update")
    @ApiOperation(value = "?????? ?????? ??????", notes = "{\n\"userPw\" : {String: ??????????????????}, \n \"userNickname\": {String: ???????????????} \n} \n??? ????????????.")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws SQLException {
        int cnt = userService.updateUser(user);

        // ?????? ??????????????? ??????
        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // ID ????????????
    @NoJwt
    @GetMapping("/checkId")
    @ApiOperation(value = "????????? ??? id ????????? ????????????.")
    public ResponseEntity<?> checkId(@RequestParam String userId) throws SQLException {
        // User ????????????
        int cnt = userService.checkId(userId);

        // ?????? ??????????????? ??????
        if (cnt != 0) return new ResponseEntity<String>(FAIL, HttpStatus.OK); //????????? ???????????? ?????? ?????????
        else return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // ????????? ????????????
    @NoJwt
    @GetMapping("/checkNick")
    @ApiOperation(value = "????????? ?????? ??????", notes = "???????????? ??? ????????? ????????? ????????????.")
    public ResponseEntity<?> checkNickname(@RequestParam String userNickname) throws SQLException {
        // User ????????????
        int cnt = userService.checkNickname(userNickname);

        // ?????? ??????????????? ??????
        if (cnt != 0) return new ResponseEntity<String>(FAIL, HttpStatus.OK); //????????? ???????????? ?????? ?????????
        else return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // ????????????
    @NoJwt
    @PostMapping("/regist")
    @ApiOperation(value = "????????????", notes = "{\n \"userId\": {String:???????????????},\n \"userPw\":{String:??????????????????},\n \"userEmail\":{String:???????????????},\n \"userName\":{String:????????????},\n \"userNickName\":{String:???????????????}\n}")
    public ResponseEntity<?> registUser(@RequestBody User user) throws SQLException {    	
        int cnt = userService.registUser(user);
        
        // ?????? ??????????????? ??????
        if (cnt == 1) 
        	return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // ?????? - Patch
    @PatchMapping("/quit/{userId}")
    @ApiOperation(value = "?????? ??????")
    @ApiImplicitParam(name = "userId", paramType = "path")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) throws SQLException {
        System.out.println(userId);
        User user = new User();
        user.setUserId(userId);
        int cnt = userService.deleteUser(user);

        //?????? ??????????????? ??????
        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // ????????? ???????????? ?????? - DELETE
    @DeleteMapping("/{userId}")
    @ApiOperation(value = "????????? ???????????? ??????")
    @ApiImplicitParam(name = "userId", paramType = "path")
    public ResponseEntity<?> adminDeleteUser(@PathVariable("userId") String userId) throws SQLException {
        int cnt = userService.adminDeleteUser(userId);

        //?????? ??????????????? ??????
        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // ????????? ??????
    @NoJwt
    @GetMapping("/findid")
    @ApiOperation(value = "????????? ??????")
    @ApiImplicitParam(name = "userEmail", paramType = "query")
    public ResponseEntity<?> findId(@RequestParam String userEmail) throws SQLException {
        String userId = userService.findId(userEmail);

        if (userId != null) { //?????? ???????????? ????????? ????????? ??????
            return new ResponseEntity<String>(userId, HttpStatus.OK);
        } else { //?????? ???????????? ????????? ????????? ??????
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    // ???????????? ?????? -> ???????????? ?????? ???????????? ??????
    @NoJwt
    @GetMapping("/findpw")
    @ApiOperation(value = "???????????? ??????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId"),
            @ApiImplicitParam(name = "userEmail")
    })
    public ResponseEntity<?> getTmpPw(@RequestParam String userId, @RequestParam String userEmail) throws SQLException {

        int result = userService.checkId(userId);

        if (result == 0) { //???????????? ?????? ??????
            return new ResponseEntity<String>("?????? ID??? ????????? ????????? ????????????.", HttpStatus.OK);
        } else { //???????????? ??????
            User user = userService.myPage(userId);

            if (userEmail.equals(user.getUserEmail())) {
                userService.makeTmpPw(userId);
                return new ResponseEntity<String>("?????? ??????????????? ??????????????????. ???????????? ??????????????????.", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("?????? Email??? ???????????? ????????????.", HttpStatus.OK);
            }

        }
    }


    //?????? ???????????? ??????
    @PostMapping("/hashtag")
    @ApiOperation(value = "???????????? ??????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "???????????????"),
    })
    public ResponseEntity<?> addUserHashtag(@RequestParam String userId, @RequestBody List<String> hashtagNameList) throws SQLException {
        
    	int cnt = hashtagService.addUserHashtagList(hashtagNameList, userId);
    	
        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    //?????? ???????????? ??????
    @DeleteMapping("/hashtag")
    @ApiOperation(value = "???????????? ??????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "???????????????"),
            @ApiImplicitParam(name = "hashtagNum", value = "???????????? NUM", dataType = "int")
    })
    public ResponseEntity<?> deleteUserHashtag(@RequestParam String userId, @RequestParam int hashtagNum) throws SQLException {
        User user = userService.myPage(userId);
        Hashtag hashtag = hashtagService.getHashtag(hashtagNum);

        int cnt = userHashtagService.deleteUserHashtag(user, hashtag);

        if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    //?????? ???????????? ??????
    @GetMapping("/hashtag")
    @ApiOperation(value = "?????? ???????????? ?????? ??????")
    @ApiImplicitParam(name = "userId", value = "?????? ?????????")
    public ResponseEntity<?> getUserHashtagList(@RequestParam String userId) throws SQLException {
        User user = userService.myPage(userId);
        
        System.out.println(user.getUserId());
        List<UserHashtag> list = userHashtagService.getUserHashtag(user);

        if (list.isEmpty()) {
        	// list result in none
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }

        // convert to string list
        List<String> ret_list = new ArrayList<>();
        for (UserHashtag uht : list) {
            ret_list.add(uht.getHashtag().getHashtagName());
        }
        
        return new ResponseEntity<List<String>>(ret_list, HttpStatus.OK);
    }

    // ?????? ????????? ?????????
    // ????????? ??????
    @PutMapping("/profile/{userId}")
    @ApiOperation(value = "????????? ????????? ????????????.", notes = "\"{\n\\\"profileSelfIntroduce\\\" : {String: ????????????}\n}")
    public ResponseEntity<?> updateProfile(HttpServletRequest request, @RequestBody User user, @PathVariable("userId") String userId) throws SQLException {
        String requestUserId = jwtTokenProvider.getUserID(request.getHeader("access-token"));
        if (requestUserId.equals(userId)) {
            int cnt = profileService.updateProfile(userId, user);

            if (cnt == 1) {
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(FAIL, HttpStatus.OK);
            }
        } else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // ????????? ?????? ????????????
    @GetMapping("/profile/{userId}")
    @ApiOperation(value = "???????????? ????????????.")
    public ResponseEntity<?> getProfile(@PathVariable("userId") String userId) throws SQLException {

        User userProfile = profileService.getProfile(userId);

        return new ResponseEntity<User>(userProfile, HttpStatus.OK);
    }

    // ????????? ????????? ??????
    @PostMapping("/profile/{userId}")
    @ApiOperation(value = "?????? ????????? ????????? ??????/??????/??????", notes = "????????? ?????? ?????? - ????????? ?????? ?????????&?????? \n null??? ?????? ?????? - ????????? ?????? ??????")
    public ResponseEntity<?> addProfileImage(@PathVariable("userId") String userId, @RequestPart MultipartFile pic) throws IOException, SQLException {
        UUID uuid = UUID.randomUUID();
        String profileImg = uuid.toString();

        String path = profileImgPath;

        File makeFolder = new File(path);

        if (!makeFolder.exists()) {
            makeFolder.mkdir();
        } else {
            System.out.println("?????? ?????? ?????????");
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
    
    @PutMapping("/profile/planner")
    @ApiOperation(value = "????????? ?????? ?????? ??????", notes = "??????,??????,?????? ????????? ?????? ??????\r\n{\r\n\"profilePlannerSet\" : \"0,1,2\"\r\n}")
    public ResponseEntity<?> updatePlannerSet(@RequestBody User user, HttpServletRequest request) throws SQLException{
    	String userId = jwtTokenProvider.getUserID(request.getHeader("access-token"));
    	int cnt = profileService.updatePlannerSet(userId, user.getProfilePlannerSet());
    	
    	if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    // ?????????
    // ????????? ??????
    @PostMapping("/follow/{userId}")
    @ApiOperation("????????? ??????")
    public ResponseEntity<?> userFollow(HttpServletRequest request, @PathVariable("userId") String followToUserId) throws SQLException {
        String followFromUserId = jwtTokenProvider.getUserID(request.getHeader("access-token"));

        Follow follow = followService.follow(followToUserId, followFromUserId);

        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // ????????? ??????
    @DeleteMapping("/follow/{userId}")
    @ApiOperation(value = "????????? ??????")
    public ResponseEntity<?> userUnFollow(HttpServletRequest request, @PathVariable("userId") String followToUserId) throws SQLException {
        String followFromUserId = jwtTokenProvider.getUserID(request.getHeader("access-token"));

        followService.deleteByFollowToUserAndFollowFromUser(followToUserId, followFromUserId);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // ?????? ????????? ??????
	@GetMapping("/profile/{userId}/follow")
	public ResponseEntity<?> followList (@PathVariable("userId") String userId) throws SQLException {

		return new ResponseEntity<List<User>>(followService.followList(userId), HttpStatus.OK);
	}

    @GetMapping("/profile/{userId}/follower")
    public ResponseEntity<?> followerList (@PathVariable("userId") String userId) throws SQLException {

        return new ResponseEntity<List<User>>(followService.followerList(userId), HttpStatus.OK);
    }

    @NoJwt
    @GetMapping("/confirmEmail")
    public ResponseEntity<?> confirmEmail(@RequestParam String email) throws SQLException {
        int cnt = userService.confirmEmail(email);
        if (cnt == 1) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } else if (cnt == 2) {
            return new ResponseEntity<String >("alreadyRegistEmail", HttpStatus.OK);

        }
        return new ResponseEntity<String>(FAIL,HttpStatus.OK);
    }

    @NoJwt
    @PostMapping("/confirmCode")
    public ResponseEntity<?> confirmCode (@RequestBody Map<String, String> params) throws SQLException {
        String code = params.get("code");
        String email = params.get("email");
        int cnt = userService.confirmCode(code, email);
        if (cnt == 1){
            return new ResponseEntity<String> (SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    @GetMapping("/restStudyTime/{userId}")
    public ResponseEntity<?> getRestStudyTime(@PathVariable String userId) throws SQLException {
    	User user = userService.myPage(userId);
    	String rank = user.getProfileRank();
    	int studyTime = user.getProfileTotalStudyTime();
    	
    	int restTime = 0;
    	switch(rank) {
    	case "?????????" : 
    		break;
    	case "???????????????" : 
    		restTime = 500 - studyTime;
    		break;
    	case "????????????" : 
    		restTime = 400 - studyTime;
    		break;
    	case "??????" :
    		restTime = 300 - studyTime;
    		break;
    	case "??????" :
    		restTime = 200 - studyTime;
    		break;
    	case "?????????" :
    		restTime = 100 - studyTime;
    		break;
    	}
    	
    	return new ResponseEntity<Integer> (restTime, HttpStatus.OK);
    }
    
    //?????? ?????????????????? ????????????
    @PostMapping("/playList")
    @ApiOperation(value = "?????? ????????? ????????? ????????????. " , notes = "url??????,url??????,url?????? ????????? ?????? ??????\r\n{\r\n\"musicPlayList\" : \"aa,bb,cc\"\r\n}")
    public ResponseEntity<?> setPlayList(@RequestBody User user, HttpServletRequest request) throws SQLException {
    	String userId = jwtTokenProvider.getUserID(request.getHeader("access-token"));
    	int cnt = userService.setPlayList(userId, user.getMusicPlayList());
    	
    	if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    //?????? ?????????????????? ????????????
    @GetMapping("/playList")
    @ApiOperation(value = "?????? ????????? ????????? ????????????")
    public ResponseEntity<?> getPlayList(HttpServletRequest request) throws SQLException {
    	String userId = jwtTokenProvider.getUserID(request.getHeader("access-token"));

    	User user = userService.myPage(userId);
    	
    	String result = user.getMusicPlayList();
    	
    	if (result != null) return new ResponseEntity<String>(result, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
}

