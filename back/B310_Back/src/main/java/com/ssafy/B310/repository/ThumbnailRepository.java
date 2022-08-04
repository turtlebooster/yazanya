package com.ssafy.B310.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.RoomThumbnail;

@Repository
public interface ThumbnailRepository extends JpaRepository<RoomThumbnail, Integer> {

	RoomThumbnail findByThumbnailId(String thumbnailId);

}
