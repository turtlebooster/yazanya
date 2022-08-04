package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.B310.entity.Todo;

public interface TodoService {
	// todo 등록
	int createTodo(Todo todo, String userId) throws SQLException;
	// todo 전체 조회
	List<Todo> findAllTodo() throws SQLException;
	// todo userId 로 전체 조회
	List<Todo> findTodoByUserId(String userId) throws SQLException;
	// todo num 으로 하나 조회
	Todo findTodoByTodoNum(int todoNum) throws SQLException;
	// todo 수정
	int updateTodo(Todo todo) throws SQLException;
	// todo 삭제
	int removeTodo(int todoNum) throws SQLException;
}
