package com.ssafy.B310.service;

import com.ssafy.B310.entity.Mail;

public interface MailService {
	public Mail createMail(String tmpPassword, String memberEmail);
	
	public void sendMail(Mail mail);
}
