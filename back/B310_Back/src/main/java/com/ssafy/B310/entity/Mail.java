package com.ssafy.B310.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mail {
	private String toAddress; //수신인
	private String title; //제목
	private String message; //내용
	private String fromAddress; //송신인
}
