package com.ssafy.B310.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.service.RoomService;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	RoomService roomservice;
	
	// 规 积己
	@PostMapping("/create")
	public ResponseEntity<?> createRoom(@RequestBody Room room) throws SQLException{
		
		int cnt = roomservice.createRoom(room);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 规 炼雀
	@GetMapping("/filter")
	public ResponseEntity<?> filterRoom(@RequestBody Map<String, Integer> params) throws SQLException{
		return new ResponseEntity<List<Room>>(roomservice.filterRoom(params), HttpStatus.OK);
	}

	// 规 昏力
	@GetMapping("/remove/{roomNum}")
	public ResponseEntity<?> removeRoom(@PathVariable("roomNum") int roomNum) throws SQLException{
		
		int cnt = roomservice.removeRoom(roomNum);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 规 沥焊 诀单捞飘
	@PutMapping("/update")
	public ResponseEntity<?> removeRoom(@RequestBody Room room) throws SQLException{
		
		int cnt = roomservice.updateRoom(room);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
