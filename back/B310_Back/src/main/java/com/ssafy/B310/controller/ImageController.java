package com.ssafy.B310.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.B310.annotation.NoJwt;
import com.ssafy.B310.repository.RoomRepository;
import com.ssafy.B310.repository.UserRepository;

import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/showImg")
public class ImageController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoomRepository RoomRepository;
	
	@NoJwt
	@GetMapping("/profile/{userNum}")
	@ApiOperation(value = "프로필이미지 반환", notes = "userNum 에 해당하는 유저의 프로필 이미지를 반환\r\n아이디가 잘못됐거나 저장된 이미지가 없을 경우 기본 이미지를 넘겨줌")
	public String showprofile(@PathVariable long userNum) {
		String profileImg;
		try {
			profileImg = userRepository.findById(userNum).get().getProfilePictureLink();
			if (profileImg.equalsIgnoreCase("null") || profileImg == null || profileImg.trim().equals("")) return "redirect:/image/thumbnail/study.jpg";
			profileImg = "redirect:/image/profile/" + profileImg;
		} catch (Exception e) {
			return "redirect:/image/profile/profile.png";
		}
		return profileImg;
	}
	
	@NoJwt
	@GetMapping("/thumbnail/{roomNum}")
	@ApiOperation(value = "썸네일이미지 반환", notes = "roomNum 에 해당하는 방의 썸네일 이미지를 반환\r\n방 정보가 잘못됐거나 저장된 이미지가 없을 경우 기본 이미지를 넘겨줌")
	public String showthumbnail(@PathVariable int roomNum) {
		String thumbnailImg;
		try {
			thumbnailImg = RoomRepository.findById(roomNum).get().getRoomThumbnail();
			if (thumbnailImg.equalsIgnoreCase("null") || thumbnailImg == null || thumbnailImg.trim().equals("")) return "redirect:/image/thumbnail/study.jpg";
			thumbnailImg = "redirect:/image/thumbnail/" + thumbnailImg;
		} catch (Exception e) {
			return "redirect:/image/thumbnail/study.jpg";
		}
		return thumbnailImg;
	}
}
