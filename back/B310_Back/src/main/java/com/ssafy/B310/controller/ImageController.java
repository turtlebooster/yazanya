package com.ssafy.B310.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Value("${thumbnailImg.path}")
    String thumbnailImgPath;
	
    @Value("${profileImg.path}")
    String profileImgPath;
	
    @NoJwt
	@GetMapping("/profile/number/{userNum}")
	@ApiOperation(value = "프로필이미지 반환", notes = "userNum 에 해당하는 유저의 프로필 이미지를 반환\r\n아이디가 잘못됐거나 저장된 이미지가 없을 경우 기본 이미지를 넘겨줌")
	public ResponseEntity<byte[]> showProfile(@PathVariable int userNum) {
		String path = profileImgPath;
		String filepath;
		String profileImgName;
		try {
			profileImgName = userRepository.findByUserNum(userNum).get().getProfilePictureLink();
			if (profileImgName.equalsIgnoreCase("null") || profileImgName == null || profileImgName.trim().equals(""))
				throw new Exception("profile is invalid");
			else
				filepath = path + "/" + profileImgName; 
		} catch (Exception e) {
			filepath = path + "/profile.png";
		}
		
		// get byte data in file
		File img;
		byte[] data;
		try {
			img = new File(filepath);
			data = new byte[(int)img.length()];
			// convert byte array
			FileInputStream fl = new FileInputStream(img);
			fl.read(data);
			fl.close();
		} catch (IOException e) {
			return new ResponseEntity<byte[]>(new byte[0], HttpStatus.NO_CONTENT);
		}
		// find mime type(png or jpg)
	    FileNameMap fileNameMap = URLConnection.getFileNameMap();
	    String mimeType = fileNameMap.getContentTypeFor(img.getName());

	    // byte data info
	    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", mimeType);
        headers.add("Content-Length", String.valueOf(img.length()));
		return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
	}
	
	@NoJwt
	@GetMapping("/profile/nickname/{userNickname}")
	@ApiOperation(value = "프로필이미지 반환", notes = "userNickname 에 해당하는 유저의 프로필 이미지를 반환\r\n아이디가 잘못됐거나 저장된 이미지가 없을 경우 기본 이미지를 넘겨줌")
	public ResponseEntity<byte[]> showProfileWithNickname(@PathVariable String userNickname) {
		String path = profileImgPath;
		String filepath;
		String profileImgName;
		try {
			profileImgName = userRepository.findByUserNickname(userNickname).get().getProfilePictureLink();
			if (profileImgName.equalsIgnoreCase("null") || profileImgName == null || profileImgName.trim().equals(""))
				throw new Exception("profile is invalid");
			else
				filepath = path + "/" + profileImgName; 
		} catch (Exception e) {
			filepath = path + "/profile.png";
		}
		
		// get byte data in file
		File img;
		byte[] data;
		try {
			img = new File(filepath);
			data = new byte[(int)img.length()];
			// convert byte array
			FileInputStream fl = new FileInputStream(img);
			fl.read(data);
			fl.close();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<byte[]>(new byte[0], HttpStatus.NO_CONTENT);
		}
		// find mime type(png or jpg)
	    FileNameMap fileNameMap = URLConnection.getFileNameMap();
	    String mimeType = fileNameMap.getContentTypeFor(img.getName());

	    // byte data info
	    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", mimeType);
        headers.add("Content-Length", String.valueOf(img.length()));
		return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
	}
	
	@NoJwt
	@GetMapping("/thumbnail/{roomNum}")
	@ApiOperation(value = "썸네일이미지 반환", notes = "roomNum 에 해당하는 방의 썸네일 이미지를 반환\r\n방 정보가 잘못됐거나 저장된 이미지가 없을 경우 기본 이미지를 넘겨줌")
	public ResponseEntity<byte[]> showthumbnail(@PathVariable int roomNum) {
		String path = thumbnailImgPath;
		String filepath;
		String thumbnailName;
		try {
			// get file name in server
			thumbnailName = RoomRepository.findById(roomNum).get().getRoomThumbnail();
			if (thumbnailName.equalsIgnoreCase("null") || thumbnailName == null || thumbnailName.trim().equals(""))
				throw new Exception("thumbnail is invalid");
			else 
				filepath = path + "/" + thumbnailName;
		} catch (Exception e) {
			filepath = path + "/study.jpg";
		}
		
		// get byte data in file
		File img;
		byte[] data;
		try {
			img = new File(filepath);
			data = new byte[(int)img.length()];
			// convert byte array
			FileInputStream fl = new FileInputStream(img);
			fl.read(data);
			fl.close();
		} catch (IOException e) {
			return new ResponseEntity<byte[]>(new byte[0], HttpStatus.NO_CONTENT);
		}
		// find mime type(png or jpg)
	    FileNameMap fileNameMap = URLConnection.getFileNameMap();
	    String mimeType = fileNameMap.getContentTypeFor(img.getName());

	    // byte data info
	    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", mimeType);
        headers.add("Content-Length", String.valueOf(img.length()));
		return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
	}
}
