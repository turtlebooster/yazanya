package com.ssafy.B310.service;

import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.UserQueryRepository;
import com.ssafy.B310.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserQueryRepository userQueryRepo;

    @Override
    public int updateProfile(String userId, User user) {
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User u = oUser.get();
            u.setProfileSelfIntroduce(user.getProfileSelfIntroduce());
            // 유저 닉네임 변경시 중복 검사 해 줄 것.
//            u.setUserNickname(user.getUserNickname());
            userRepository.save(u);
            return 1;
        }
        return 0;
    }
    @Override
    public User getProfile(String userId) {
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User u = oUser.get();
            
            userQueryRepo.setRank(u);
            
            return u;
        }
        return null;
    }

    //프로필 사진 업로드
    @Override
    public int uploadProfileImg(String userId, String imgPath) throws SQLException {
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User u = oUser.get();
            System.out.println(imgPath);
            u.setProfilePictureLink(imgPath);
            userRepository.save(u);
            return 1;
        }
        return 0;
    }
    
    // 플래너 세팅 수정
	@Override
	public int updatePlannerSet(String userId, String profilePlannerSet) throws SQLException {
        Optional<User> oUser = userRepository.findByUserId(userId);
        if (oUser.isPresent()) {
            User u = oUser.get();
            u.setProfilePlannerSet(profilePlannerSet);     
            userRepository.save(u);
            return 1;
        }
        return 0;
	}
}
