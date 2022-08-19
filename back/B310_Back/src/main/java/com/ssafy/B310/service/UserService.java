package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.B310.entity.User;

public interface UserService {

	List<User> selectUserList() throws SQLException;
	String  login(User user) throws SQLException;
	int registUser(User user) throws SQLException;
	int updateUser(User user) throws SQLException;
	int deleteUser(User user) throws SQLException;
	int adminDeleteUser(String userId) throws SQLException;
	User myPage(String userId) throws SQLException;
	int checkId(String userId) throws SQLException;
	int checkNickname(String userNickname) throws SQLException;
	String findId(String userEmail) throws SQLException;
	String findPw(User user) throws SQLException;
	String makeTmpPw(String userId) throws SQLException;
	String hashPw(String userPw);
	int confirmEmail(String email) throws SQLException;
	int confirmCode(String code, String email);
	int setPlayList(String userId, String profilePlannerSet) throws SQLException;
}
