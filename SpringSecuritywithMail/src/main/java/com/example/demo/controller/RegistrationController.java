package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.events.UserRegistrationEvent;
import com.example.demo.model.MyAppUser;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserRegistrationService;
import com.example.demo.service.VerificationService;




@Controller
public class RegistrationController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private  UserRegistrationService service;
	
	@Autowired
	private UserRepository userRepositry;
	//TODO-15 Uncomment the below to autowire ApplicationEventPublisher
	
	@Autowired
	private  JavaMailSender mailSender;
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private VerificationService verificationService;
	
	@Autowired
	private  PasswordEncoder encoder;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new UserDto());
		return "register";
	}

	@GetMapping("/ForgetPassword")
	public String forget(Model model) {
		model.addAttribute("user",new UserDto());
		return "EmailEnter";
	}
	@PostMapping("/ForgetPassword")
	public String forget1(Model model,@RequestParam String email) {
	
		String resetToken=generateRandomString();
		MyAppUser usr = userRepositry.findByEmail(email);
		if(usr!=null)
		{usr.setResettoken(resetToken);
		userRepositry.save(usr);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("puja kumari  Verifiying you via email");
		message.setText("login link: http://localhost:8080/verify/emailForPassword?resetToken="+resetToken);
		message.setTo(email);
		mailSender.send(message);
		
		
		System.err.println("**************Mail Sentttttttttttttttt");
		return "confirmation";}
		return "error";
	}
	@PostMapping("/changePassword")
	@ResponseBody
	public String changed(@RequestParam String password,@RequestParam
			MyAppUser user)
	{
		user.setPassword(passwordEncoder.encode(password));
		userRepositry.save(user);
		return "Password changed";
	}
	
	@GetMapping("/verify/emailForPassword")
	public String ResetPassword(@RequestParam String resetToken,Model model)
	{
		MyAppUser user = userRepositry.findByResettoken(resetToken);
		
	
		model.addAttribute("user",user);
		
		if(user != null) {
			user.setVerified(1);
			userRepositry.save(user);
			return "resetPassword";
		}
		return "error";
	}
	
	
	
	
	
	public static  String generateRandomString() 
	{
		
		String fullString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz"; 
		StringBuilder sb = new StringBuilder(20); 
		
		
		for(int i=0;i<fullString.length();i++) 
		{
			 int index =(int)(fullString.length() * Math.random()); 

         // add Character one by one in end of sb 
         sb.append(fullString .charAt(index)); 
		}
		
		return sb.toString();
	}
	
	
	
	
	
	
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") UserDto user, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}
		
		//TODO-16 In the below MyAppUser Constructor call, make the last parameter as false to disable the user

		
		MyAppUser myuser = new MyAppUser(user.getUsername(), 
				  user.getFirstname(), 
				  user.getLastname(),
				  user.getEmail(), 
				  user.getNumber(),
				  encoder.encode(user.getPassword()),false);
		service.createUser(myuser);
		
		//TODO-17 uncomment the below line to publish UserRegistrationEvent
		
		eventPublisher.publishEvent(new UserRegistrationEvent(myuser));
		return "redirect:register?success";
	}
	
}
