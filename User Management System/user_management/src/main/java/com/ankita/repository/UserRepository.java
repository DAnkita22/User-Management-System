package com.ankita.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankita.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer>{

	public boolean existsByEmail(String email);
	
	Optional<com.ankita.model.UserDetails> findByEmail(String email);
	
}
