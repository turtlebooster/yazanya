package com.ssafy.B310.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ssafy.B310.jwt.JwtTokenProvider;
import com.ssafy.B310.service.TodoService;
import com.ssafy.B310.service.UserService;

import io.swagger.annotations.ApiOperation;

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
    @Autowired
    JwtTokenProvider jwtService;
    
    @ApiOperation(value="todo 생성", notes="userId[HttpServletRequest] - 누구한테 할 일을 추가할지. todo[ResponseBody] - 무슨 일을 추가할지\n"
    		+ "todoNum은 AI이므로 넣을 필요 X\n"
    		+ "{\n\"todoContent\":\"[todo의 상세내용]\""
    		+ "\n\"todoEndTime\":\"[todo의 마감시간]\""
    		+ "\n\"todoName\":\"[todo의 제목]\""
    		+ "\n\"todoProgress\": 0 (아직 못끝냄) or 1 (끝냄)"
    		+ "\n\"todoStartTime\":\"[todo의 시작시간]\"\n}")
    @PostMapping()
    public ResponseEntity<?> createTodo(@RequestBody Todo todo, HttpServletRequest request) throws SQLException{
		String userId = jwtService.getUserID(request.getHeader("access-token"));
    	int cnt = todoService.createTodo(todo, userId);
    	if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    @ApiOperation(value="todo 전제조회", notes="DB에 기록되어있는 모든 todo들을 불러온다")
    @GetMapping
    public ResponseEntity<?> findAllTodo() throws SQLException{
    	return new ResponseEntity<List<Todo>>(todoService.findAllTodo(), HttpStatus.OK);
    }
    
    @ApiOperation(value="todo 유저별 조회", notes="특정 유저의 todo를 불러온다\nPathVariable로 원하는 유저의 Id를 넣는다.")
    @GetMapping("/{userId}")
    public ResponseEntity<?> findTodoByUserId(@PathVariable("userId") String userId) throws SQLException{
    	List<Todo> todoList = todoService.findTodoByUserId(userId);
    	if (todoList != null) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("todoList", todoList);
    		map.put("achievement", todoService.calAchievement(todoList));
    		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);    		
    	} else {
    		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    	}
    }
    
    @ApiOperation(value="단일 todo 조회", notes="특정 todo만을 불러온다.\nPathVariable로 원하는 todoNum을 넣는다.")
    @GetMapping("/findbytodonum/{todoNum}")
    public ResponseEntity<?> findTodoByTodoNum(@PathVariable("todoNum") int todoNum) throws SQLException{
    	Todo todo = todoService.findTodoByTodoNum(todoNum);
    	if (todo != null) {
    		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    	} else {
    		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    	}
    }
    
    @ApiOperation(value="todo 수정", notes="todo를 수정한다.\n수정할 todo를 RequestBody로 전달한다."
    		+"\n여기서는 todoNum도 같이 넣어줘야 한다.\n"
    		+ "{\n\"todoNum\":[todo의 번호]"
    		+ "\n\"todoContent\":\"[todo의 상세내용]\""
    		+ "\n\"todoEndTime\":\"[todo의 마감시간]\""
    		+ "\n\"todoName\":\"[todo의 제목]\""
    		+ "\n\"todoProgress\": 0 (아직 못끝냄) or 1 (끝냄)"
    		+ "\n\"todoStartTime\":\"[todo의 시작시간]\"\n}")
    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody Todo todo) throws SQLException{
    	int cnt = todoService.updateTodo(todo);
    	if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    @ApiOperation(value="todo 삭제", notes="특정 todo를 삭제한다.\nPathVariable로 삭제할 todoNum을 넣는다.")
    @DeleteMapping("/{todoNum}")
    public ResponseEntity<?> removeTodo(@PathVariable("todoNum") int todoNum) throws SQLException{
    	int cnt = todoService.removeTodo(todoNum);
    	if (cnt == 1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
    
    @ApiOperation(value="todo 날짜별 조회", notes="특정 날의 todo 목록을 불러온다.\nRequestBody로 불러오고싶은 시작 날짜와 끝 날짜를 넣는다.\n"
    		+ "{\n\"startDate\" : \"[시작할 날짜]\""
    		+ "\n\"endDate\" : \"[끝 날짜]\""
    		+ "\n}")
    @GetMapping("/findbydaterange")
    public ResponseEntity<?> findTodoByDate(@RequestBody Map<String, Date> map, HttpServletRequest request) throws SQLException{
    	String userId = jwtService.getUserID(request.getHeader("access-token"));
    	Date startDate = map.get("startDate");
    	Date endDate = map.get("endDate");
    	
    	int compare = startDate.compareTo(endDate);
    	if (compare > 0) {
    		System.out.println("날짜 범위 오류입니다.");
    		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    	} else if (compare == 0) {
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(endDate);
    		cal.add(Calendar.HOUR, 24);
    		endDate = cal.getTime();
    	}
    	
		List<Todo> todoList = todoService.findTodoByDateRange(userId, startDate, endDate);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("todoList", todoList);
		resultMap.put("achievement", todoService.calAchievement(todoList));
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

	@GetMapping("/achievement")
	public ResponseEntity<?> getAchievement(HttpServletRequest request) throws SQLException {
		String userId = jwtService.getUserID(request.getHeader("access-token"));
		List<Todo> todoList = todoService.findTodoByUserId(userId);
		return new ResponseEntity<Double>(todoService.calAchievement(todoList), HttpStatus.OK);
	}
}
