package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.events.UserRegistrationEvent;


@Service

public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

	@Autowired
	private  JavaMailSender mailSender;
	@Autowired
	private VerificationService verificationService;
	
	
	
	@Override
	public void onApplicationEvent(UserRegistrationEvent event) {
	
		String username = event.getUser().getUsername();
		Long verificationId = verificationService.createVerification(username);		
		String email = event.getUser().getEmail();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("puja kumari  Verifiying you via email");
		message.setText("login link: https://localhost:8080/verify/email?id="+verificationId);
		message.setTo(email);
		mailSender.send(message);
		
		
		System.err.println("**************Mail Sentttttttttttttttt");
	}

}
