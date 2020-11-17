package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Verification;

public interface VerificationCodeRepository extends CrudRepository<Verification, Long>{
	
	Verification findByUsername(String username);
	boolean existsByUsername(String username);
}
