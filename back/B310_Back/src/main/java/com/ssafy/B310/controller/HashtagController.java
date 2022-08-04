package com.ssafy.B310.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.service.HashtagService;

@RestController
@RequestMapping("/hashtag")
public class HashtagController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	HashtagService hashtagService;
	
	//해쉬태그 생성
	@PostMapping("/create")
	public ResponseEntity<?> createHashtag(@RequestBody Hashtag hashtag) throws SQLException {
		int cnt = hashtagService.createHashtag(hashtag);
	
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	//해쉬태그 생성
	@PutMapping("/update")
	public ResponseEntity<?> updateHashtag(@RequestBody Hashtag hashtag) throws SQLException {
		int cnt = hashtagService.updateHashtag(hashtag);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//해쉬태그 삭제
	@DeleteMapping("/remove/{hashtagNum}")
	public ResponseEntity<?> removeHashtag(@PathVariable("hashtagNum") int hashtagNum) throws SQLException {
		int cnt = hashtagService.removeHashtag(hashtagNum);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	// 해쉬태그 전체 리스트 전달
	@GetMapping
	public ResponseEntity<?> getHashtagList() {
		return new ResponseEntity<List<Hashtag>>(hashtagService.getHashtagList(), HttpStatus.OK);
	}

}
