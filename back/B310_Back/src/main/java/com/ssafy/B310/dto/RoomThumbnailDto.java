package com.ssafy.B310.dto;

public class RoomThumbnailDto {
	private String uuid;	//유니크한 파일 이름
	private String fileName;	//실제 파일 이름
	
	public RoomThumbnailDto(String uuid, String fileName) {
		super();
		this.uuid = uuid;
		this.fileName = fileName;
	}
	public RoomThumbnailDto() {
		super();
	}

}
