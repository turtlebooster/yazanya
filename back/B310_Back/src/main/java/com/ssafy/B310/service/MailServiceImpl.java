package com.ssafy.B310.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.B310.entity.Mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;
    
    private static final String title = "YaZaNya �ӽ� ��й�ȣ �ȳ� �̸����Դϴ�.";
    private static final String message = "�ȳ��ϼ���. YaZaNya �ӽ� ��й�ȣ �ȳ� �����Դϴ�. "
            +"\n" + "ȸ������ �ӽ� ��й�ȣ�� �Ʒ��� �����ϴ�. �α��� �� �ݵ�� ��й�ȣ�� �������ּ���."+"\n";
    private static final String fromAddress = "helpYaZaNya@gmail.com";
	
    
    @Override
	public Mail createMail(String tmpPassword, String memberEmail) {
		Mail mail = Mail.builder()
                .toAddress(memberEmail)
                .title(title)
                .message(message + tmpPassword)
                .fromAddress(fromAddress)
                .build();

        return mail;
	}

	@Override
	public void sendMail(Mail mail) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getToAddress());
        mailMessage.setSubject(mail.getTitle());
        mailMessage.setText(mail.getMessage());
        mailMessage.setFrom(mail.getFromAddress());
        mailMessage.setReplyTo(mail.getFromAddress());

        mailSender.send(mailMessage);

	}

}
