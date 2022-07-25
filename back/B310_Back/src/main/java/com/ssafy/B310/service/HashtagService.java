package com.ssafy.B310.service;

import java.util.List;

import com.ssafy.B310.entity.Hashtag;
import com.ssafy.B310.entity.Room;

public interface HashtagService {
	// 해쉬태그 생성
	int createHashtag(Hashtag hashtag);
	// 해쉬태그 수정
	int updateHashtag(Hashtag hashtag);
	// 해쉬태그 삭제
	int removeHashtag(int hashtagNum);
	// 해쉬태그 전체 목록 
	List<Hashtag> getHashtagList();
}
