package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.ssafy.B310.entity.EmailConfirm;
import com.ssafy.B310.repository.EmailConfirmRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Mail;
import com.ssafy.B310.entity.User;
//import com.ssafy.B310.mapper.UserMapper;
import com.ssafy.B310.repository.UserRepository;

import javax.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MailService mailService;

	@Autowired
	EmailConfirmRepository emailConfirmRepository;

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public String login(User user) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(user.getUserId());
		
		// 해당 id의 user가 있으면
		if (oUser.isPresent()) {
			User u = oUser.get();
			// 비밀번호가 틀리면
			if(!BCrypt.checkpw(user.getUserPw(), u.getUserPw())) {
				return "pwErr";
			}
			String userId = u.getUserId();
			return userId;
		}
		// 해당 id의 user가 없으면
		return "noId";
	}

	@Override
	public int registUser(User user) throws SQLException {
		// 이미 해당 아이디나 이메일로 가입된 유저가 있을 경우
		if (userRepository.findByUserId(user.getUserId()).isPresent() || userRepository.findByUserEmail(user.getUserEmail()).isPresent()) {
			return 0;
		}
		
		// 올바른 이메일 형식이 아닐 경우
		if(!user.getUserEmail().contains("@")) {
			System.out.println("올바른 이메일 형식이 아님");
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
			if (user.getUserPw() != null) u.setUserPw(hashPw(user.getUserPw()));
			if (user.getUserNickname() != null) u.setUserNickname(user.getUserNickname());
			if (user.getProfileSelfIntroduce() != null) u.setProfileSelfIntroduce(user.getProfileSelfIntroduce());
			
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

	@Override
	public int confirmEmail(String email) {
//		logger.info("이메일 데이터 전송 확인");
//		logger.info("인증변호: " + email);

		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		Optional<EmailConfirm> optionalEmail = emailConfirmRepository.findByEmail(email);
		if (optionalEmail.isPresent()) {
			return 2;
		}
//		logger.info("인증번호" + checkNum);

		String setFrom = "helpYaZaNya@gmail.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content =
				"홈페이지를 방문해주셔서 감사합니다.\n\n" +
						"<br><br>" +
						"인증번호는 " + checkNum + " 입니다.\n" +
						"<br><br>" +
						"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String num = Integer.toString(checkNum);
		EmailConfirm confirm = new EmailConfirm();
		confirm.setConfirmCode(num);
		confirm.setEmail(email);
		System.out.println(num);
		System.out.println(email);

		emailConfirmRepository.save(confirm);

		return 1;

	}

	@Override
	public int confirmCode(String code, String email) {
		Optional<EmailConfirm> optionalEmailConfirm = emailConfirmRepository.findByConfirmCode(code);
		if (optionalEmailConfirm.isPresent()){
			EmailConfirm emailConfirm = optionalEmailConfirm.get();
			if (emailConfirm.getConfirmCode().equals(code) && emailConfirm.getEmail().equals(email)) {
				return 1;
			}
		}
		return 0;
	}

}
