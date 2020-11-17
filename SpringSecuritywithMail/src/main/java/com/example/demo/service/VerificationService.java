package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Verification;
import com.example.demo.repository.VerificationCodeRepository;



@Service

public class VerificationService {
	
	@Autowired
	private  VerificationCodeRepository repository;
		
	public Long getVerifictionIdByUsername(String username) {
		Verification verification = repository.findByUsername(username);
		if(verification != null) {
			return verification.getId();
		}
		return null;
	}
	
	public Long createVerification(String username) {
		if (!repository.existsByUsername(username)) {
			Verification verification = new Verification(username);
			verification = repository.save(verification);
			return verification.getId();
		}
		return getVerifictionIdByUsername(username);
	}
	
	public String getUsernameForId(Long id) {
		Optional<Verification> verification = repository.findById(id);
		if(verification.isPresent()) {
			return verification.get().getUsername();
		}
		return null;
	}

	public Long createVerificationUsingEmail(String email) {
	
		return null;
	}
	
}
