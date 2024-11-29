package com.ankita.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ankita.model.UserDetails;
import com.ankita.repository.UserRepository;

@Service
public class UserServiceImple implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Override
	public UserDetails createUser(UserDetails user) {
		
		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		return userRepo.save(user);
	}

	@Override
	public boolean checkEmail(String email) {
		
		return userRepo.existsByEmail(email);
		
	}

	
}
