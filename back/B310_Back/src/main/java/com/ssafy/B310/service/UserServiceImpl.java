package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.dto.TokenResponse;
import com.ssafy.B310.entity.Mail;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.jwt.JwtTokenProvider;
import com.ssafy.B310.repository.AuthRepository;
//import com.ssafy.B310.mapper.UserMapper;
import com.ssafy.B310.repository.UserRepository;

import io.jsonwebtoken.Claims;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MailService mailService;
	
	@Override
	public User login(User user) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(user.getUserId());
		
		// 해당 id의 user가 있으면
		if (oUser.isPresent()) {
			User u = oUser.get();
			if(BCrypt.checkpw(user.getUserPw(), u.getUserPw())) {
				return u;
			}
		}
		// 해당 id의 user가 없거나, 비밀번호가 맞지 않으면
		return null;
	}

	@Override
	public int registUser(User user) throws SQLException {
		System.out.println(user);
		
		// 이미 해당 아이디나 이메일로 가입된 유저가 있을 경우
		if (userRepository.findByUserId(user.getUserId()).isPresent() || userRepository.findByUserEmail(user.getUserEmail()).isPresent()) {
			return 0;
		}
		
		// 없을 경우
		//비밀번호 암호화
		String hashPw = hashPw(user.getUserPw());
		System.out.println(hashPw);
		user.setUserPw(hashPw);
		user.setProfilePictureLink("profile.png");
		userRepository.save(user);
		
		return 1;
	}
	
	@Override
	public String hashPw(String userPw) {
		return BCrypt.hashpw(userPw, BCrypt.gensalt());
	}

	@Override
	public int updateUser(User user) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(user.getUserId());
		
		if (oUser.isPresent()) {
			User u = oUser.get();
			
			String hashPw = hashPw(user.getUserPw());
			u.setUserPw(hashPw);
			u.setUserNickname(user.getUserNickname());
			
			userRepository.save(u);
			
			return 1;
		}
		
		return 0;
	}

	@Override
	public int deleteUser(User user) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(user.getUserId());
		
		if (oUser.isPresent()) {
			userRepository.delete(oUser.get());
			return 1;
		}
		
		return 0;
	}

	@Override
	public User myPage(String userId) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(userId);
		
		if(oUser.isPresent())
			return oUser.get();
		else return null;
	}

	@Override
	public List<User> selectUserList() throws SQLException {
		return userRepository.findAll();
	}

	@Override
	public int adminDeleteUser(String userId) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(userId);
		
		if (oUser.isPresent()) {
			userRepository.delete(oUser.get());
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public int checkId(String userId) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(userId);
		
		if (oUser.isPresent()) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public int checkNickname(String userNickname) throws SQLException {
		Optional<User> oUser = userRepository.findByUserNickname(userNickname);
		
		if (oUser.isPresent()) {
			return 1;
		}
		
		return 0;
	}

	@Override
	public String findId(String userEmail) throws SQLException {
		Optional<User> oUser = userRepository.findByUserEmail(userEmail);
		
		if (oUser.isPresent()) {
			return oUser.get().getUserId();
		}
		
		return null;
	}

	@Override
	public String findPw(User user) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(user.getUserId());
		
		if (oUser.isPresent()) {
			User u = oUser.get();
			if (u.getUserEmail().equals(user.getUserEmail()))
			return u.getUserPw();
		}
		
		return null;
	}

	@Override
	public String makeTmpPw(String userId) throws SQLException {
		char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		String pwd = "";
		

		/* 10 자리의 랜덤 임시 비밀번호 생성 */
        int idx = 0;
        for(int i = 0; i < 10; i++){
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }
        
        User user = myPage(userId);
        
        System.out.println(user);
        
		String hashPw = hashPw(pwd);
		
        user.setUserPw(hashPw);
        updateUser(user);
        
        //메일 생성
        Mail mail = mailService.createMail(pwd, user.getUserEmail());
        
        System.out.println("mail is " + mail);
        
        //메일 보내기
        mailService.sendMail(mail);
        
		return pwd;
	}
	
	
//	 public TokenResponse issueAccessToken(HttpServletRequest request){
//	        String accessToken = jwtTokenProvider.resolveAccessToken(request);
//	        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
//	        
//	        System.out.println("accessToken = " + accessToken);
//	        System.out.println("refreshToken = " + refreshToken);
//	        
//	        //accessToken이 만료됐고 refreshToken이 맞으면 accessToken을 새로 발급(refreshToken의 내용을 통해서)
//	        if(!jwtTokenProvider.isValidAccessToken(accessToken)){  //클라이언트에서 토큰 재발급 api로의 요청을 확정해주면 이 조건문은 필요없다.
//	            System.out.println("Access 토큰 만료됨");
//	            
//	            if(jwtTokenProvider.isValidRefreshToken(refreshToken)){     //들어온 Refresh 토큰이 유효한지
//	                System.out.println("Refresh 토큰은 유효함");
//	                
//	                Claims claimsToken = jwtTokenProvider.getClaimsToken(refreshToken);
//	                String userId = (String)claimsToken.get("userId");
//	                
//	                Optional<User> user = userRepository.findByUserId(userId);
//	                String tokenFromDB = authRepository.findByuser_userId(user.get().getUserId()).get().getRefreshToken();
//	                
//	                System.out.println("tokenFromDB = " + tokenFromDB);
//	                
//	                if(refreshToken.equals(tokenFromDB)) {   //DB의 refresh토큰과 지금들어온 토큰이 같은지 확인
//	                    System.out.println("Access 토큰 재발급 완료");
//	                    accessToken = jwtTokenProvider.createAccessToken(userId);
//	                }
//	                else{
//	                    //DB의 Refresh토큰과 들어온 Refresh토큰이 다르면 중간에 변조된 것임
//	                    System.out.println("Refresh Token Tampered");
//	                    //예외발생
//	                }
//	            }
//	            else{
//	                //입력으로 들어온 Refresh 토큰이 유효하지 않음
//	            }
//	        }
//	        return TokenResponse.builder()
//	                .ACCESS_TOKEN(accessToken)
//	                .REFRESH_TOKEN(refreshToken)
//	                .build();
//	    }

}
