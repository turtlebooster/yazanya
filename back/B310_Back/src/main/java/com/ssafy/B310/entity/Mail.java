package com.ssafy.B310.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mail {
	private String toAddress; //������
	private String title; //����
	private String message; //����
	private String fromAddress; //�۽���
}
