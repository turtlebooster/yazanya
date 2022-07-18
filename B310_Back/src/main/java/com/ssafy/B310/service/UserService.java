package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.B310.dto.User;

public interface UserService {

	List<User> selectUserList() throws SQLException;
	User login(User user) throws SQLException;
	int registUser(User user) throws SQLException;
	int updateUser(User user) throws SQLException;
	int deleteUser(User user) throws SQLException;
	int adminDeleteUser(String userId) throws SQLException;
	User myPage(String userId) throws SQLException;
	int checkId(String userId) throws SQLException;
	String findId(String userEmail) throws SQLException;
	String findPw(User user) throws SQLException;
}
