package com.ankita.service;

import com.ankita.model.UserDetails;

public interface UserService {

	public UserDetails createUser(UserDetails user);
	
	public boolean checkEmail(String email);
}
