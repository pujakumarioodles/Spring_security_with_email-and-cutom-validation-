package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.MyAppUser;

public interface UserRepository extends CrudRepository<MyAppUser, String> {

	MyAppUser findByUsername(String username);
	MyAppUser findByEmail(String email);
	MyAppUser findByResettoken(String resetToken);
	
}
