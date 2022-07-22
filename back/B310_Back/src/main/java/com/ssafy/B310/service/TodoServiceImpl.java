package com.ssafy.B310.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.Todo;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.repository.UserRepository;
import com.ssafy.B310.repository.todoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	todoRepository todoRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public int createTodo(Todo todo) throws SQLException {
		todoRepository.save(todo);
		return 1;			
	}

	@Override
	public List<Todo> findAllTodo() throws SQLException {
		return todoRepository.findAll();
	}

	@Override
	public List<Todo> findTodoByUserId(String userId) throws SQLException {
		Optional<User> oUser = userRepository.findByUserId(userId);
		
		if (oUser.isPresent()) {
			User u = oUser.get();			
			return todoRepository.findByUser(u);
		}
		
		return null;
	}
	
	@Override
	public Todo findTodoByTodoNum(int todoNum) throws SQLException {
		Optional<Todo> oTodo = todoRepository.findById(todoNum);
		if(oTodo.isPresent()) {
			return todoRepository.findById(todoNum).get();			
		}
		return null;
	}
	
	@Override
	public int updateTodo(Todo todo) throws SQLException {
		Optional<Todo> oTodo = todoRepository.findById(todo.getTodoNum());
		// 해당 todo 가 있을 경우
		if (oTodo.isPresent()) {
			Todo t = oTodo.get();
			if (todo.getTodoContent() != null) t.setTodoContent(todo.getTodoContent());
			if (todo.getTodoEndTime() != null) t.setTodoEndTime(todo.getTodoEndTime());
			if (todo.getTodoName() != null) t.setTodoName(todo.getTodoName());
			if (todo.getTodoStartTime() != null) t.setTodoStartTime(todo.getTodoStartTime());
			todoRepository.save(t);
			return 1;
		}
		return 0;
	}

	@Override
	public int removeTodo(int todoNum) throws SQLException {
		Optional<Todo> oTodo = todoRepository.findById(todoNum);
		// 해당 todo 가 있을 경우
		if (oTodo.isPresent()) {
			todoRepository.deleteById(todoNum);
			return 1;
		}
		return 0;
	}
}
