package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Mail;
import com.ssafy.B310.entity.User;
//import com.ssafy.B310.mapper.UserMapper;
import com.ssafy.B310.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
//	@Autowired
//	UserMapper userMapper;
	
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
			if (u.getUserPw().equals(user.getUserPw())) {
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
		userRepository.save(user);
		return 1;
	}

	@Override
	public int updateUser(User user) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(user.getUserId());
		
		if (oUser.isPresent()) {
			User u = oUser.get();
			u.setUserPw(user.getUserPw());
			u.setUserNickname(user.getUserNickname());
			return 1;
		}
		
		return 0;
	}

	@Override
	public int deleteUser(User user) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(user.getUserId());
		
		
		if (oUser.isPresent()) {
			userRepository.delete(oUser.get());
//			userRepository.deleteByUserId(user.getUserId());
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
        
        user.setUserPw(pwd);
        updateUser(user);
        
        //메일 생성
        Mail mail = mailService.createMail(pwd, user.getUserEmail());
        
        System.out.println("mail is " + mail);
        
        //메일 보내기
        mailService.sendMail(mail);
        
		return pwd;
	}

}
