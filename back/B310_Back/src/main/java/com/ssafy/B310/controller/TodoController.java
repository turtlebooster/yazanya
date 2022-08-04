package com.ssafy.B310.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.B310.entity.Todo;
import com.ssafy.B310.service.TodoService;
import com.ssafy.B310.service.UserService;

@Controller
@RequestMapping("/todo")
@CrossOrigin("*")
public class TodoController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    
    @Autowired
    TodoService todoService;
    @Autowired
    UserService userService;
    
    // todo 생성
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo, @PathVariable String userId) throws SQLException{
//    	System.out.println(todo.getUser().getUserNum());
    	int cnt = todoService.createTodo(todo, userId);
    	if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // todo 전체 조회
    @GetMapping
    public ResponseEntity<?> findAllTodo() throws SQLException{
    	return new ResponseEntity<List<Todo>>(todoService.findAllTodo(), HttpStatus.OK);
    }
    // todo 유저별 조회
    @GetMapping("/findbyuser/{userId}")
    public ResponseEntity<?> findTodoByUserId(@PathVariable("userId") String userId) throws SQLException{
    	List<Todo> todoList = todoService.findTodoByUserId(userId);
    	if (todoList != null) {
    		return new ResponseEntity<List<Todo>>(todoList, HttpStatus.OK);    		
    	} else {
//    		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    		return new ResponseEntity<List<Todo>>(todoList, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    // todo 하나 조회
    @GetMapping("/findbytodonum/{todoNum}")
    public ResponseEntity<?> findTodoByTodoNum(@PathVariable("todoNum") int todoNum) throws SQLException{
    	Todo todo = todoService.findTodoByTodoNum(todoNum);
    	if (todo != null) {
    		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    	} else {
//    		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    		return new ResponseEntity<Todo>(todo, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    // todo 수정
    @PutMapping("/update")
    public ResponseEntity<?> updateTodo(@RequestBody Todo todo) throws SQLException{
    	int cnt = todoService.updateTodo(todo);
    	if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // todo 삭제
    @DeleteMapping("/remove/{todoNum}")
    public ResponseEntity<?> removeTodo(@PathVariable("todoNum") int todoNum) throws SQLException{
    	int cnt = todoService.removeTodo(todoNum);
    	if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
