package com.ssafy.B310.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.B310.entity.RoomThumbnail;
import com.ssafy.B310.repository.ThumbnailRepository;

@Service
public class ThumbnailServiceImpl implements ThumbnailService {

	@Autowired
	ThumbnailRepository trs;
	
	@Override
	public int saveFile(RoomThumbnail tn) {
		RoomThumbnail ntn = trs.save(tn);
		
		if(ntn != null) return 1;
		return 0;
	}

	@Override
	public RoomThumbnail getThumbnail(String id) {
		return trs.findByThumbnailId(id);
	}

}
