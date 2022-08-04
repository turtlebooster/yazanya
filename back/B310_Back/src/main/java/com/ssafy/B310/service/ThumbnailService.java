package com.ssafy.B310.service;

import com.ssafy.B310.entity.RoomThumbnail;

public interface ThumbnailService {
	//사진 저장
	public int saveFile(RoomThumbnail tn);
	//사진 경로 불러오기
	public RoomThumbnail getThumbnail(String id);
}
