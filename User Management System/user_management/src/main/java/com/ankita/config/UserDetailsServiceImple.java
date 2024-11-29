package com.ankita.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ankita.repository.UserRepository;

@Service
public class UserDetailsServiceImple implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<com.ankita.model.UserDetails> userOptional = userRepo.findByEmail(email);
	    com.ankita.model.UserDetails user = userOptional
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	    
	    return new CostumUserDetails(user);
	}   

}
