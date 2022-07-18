package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.dto.User;
import com.ssafy.B310.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User login(User user) throws SQLException {
		return userMapper.login(user);
	}

	@Override
	public int registUser(User user) throws SQLException {
		System.out.println(user);
		return userMapper.registUser(user);
	}

	@Override
	public int updateUser(User user) throws SQLException {
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(User user) throws SQLException {
		return userMapper.deleteUser(user);
	}

	@Override
	public User myPage(String userId) throws SQLException {
		return userMapper.myPage(userId);
	}

	@Override
	public List<User> selectUserList() throws SQLException {
		return userMapper.selectUserList();
	}

	@Override
	public int adminDeleteUser(String userId) throws SQLException {
		return userMapper.adminDeleteUser(userId);
	}
	
	@Override
	public int checkId(String userId) throws SQLException {
		return userMapper.checkId(userId);
	}

	@Override
	public String findId(String userEmail) throws SQLException {
		return userMapper.findId(userEmail);
	}

	@Override
	public String findPw(User user) throws SQLException {
		return userMapper.findPw(user);
	}

}
