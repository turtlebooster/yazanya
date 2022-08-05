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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hashtag")
public class HashtagController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	HashtagService hashtagService;
	
	@ApiOperation(value="해쉬태그 생성", notes="hashtagNum은 AI이므로 hashtagName만 넣어준다.\n{ \n\"hashtagName\" : [생성할 hashtag명] \n}")
	@PostMapping
	public ResponseEntity<?> createHashtag(@RequestBody Hashtag hashtag) throws SQLException {
		int cnt = hashtagService.createHashtag(hashtag);
	
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.OK);

	}
	
	@ApiOperation(value="해쉬태그 수정", notes="{\n \"hashtagNum\" : [해당 hashtagNum] \n\n\"hashtagName\" : [원하는 hashtag명] \n}")
	@PutMapping
	public ResponseEntity<?> updateHashtag(@RequestBody Hashtag hashtag) throws SQLException {
		int cnt = hashtagService.updateHashtag(hashtag);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value="해쉬태그 삭제", notes="hashtagNum을 PathVariable로 받아 해당 hashtag를 삭제한다.")
	@DeleteMapping("/{hashtagNum}")
	public ResponseEntity<?> removeHashtag(@PathVariable("hashtagNum") int hashtagNum) throws SQLException {
		int cnt = hashtagService.removeHashtag(hashtagNum);
		
		if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else return new ResponseEntity<String>(FAIL, HttpStatus.OK);

	}
	
	@ApiOperation(value="해쉬태그 리스트", notes="모든 hashtag 리스트를 불러온다")
	@GetMapping
	public ResponseEntity<?> getHashtagList() {
		return new ResponseEntity<List<Hashtag>>(hashtagService.getHashtagList(), HttpStatus.OK);
	}

}
