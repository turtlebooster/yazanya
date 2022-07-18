package com.ssafy.B310.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.B310.dto.User;

@Mapper
public interface UserMapper {
	List<User> selectUserList() throws SQLException;
	User login(User user) throws SQLException;
	int registUser(User user) throws SQLException;
	int updateUser(User user) throws SQLException;
	int deleteUser(User user) throws SQLException;
	int adminDeleteUser(String userId) throws SQLException;
	User myPage(String userNum) throws SQLException;
	int checkId(String userId) throws SQLException;
	String findId(String userEmail) throws SQLException;
	String findPw(User user) throws SQLException;
}
