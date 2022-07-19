package com.ssafy.B310.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.service.RoomService;
import com.ssafy.B310.service.UserService;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	RoomService roomservice;
	
	@PostMapping
	public ResponseEntity<?> registRoom(@RequestBody Room room) throws SQLException{
		
		int cnt = RoomService.registRoom(room);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
