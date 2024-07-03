package com.arjun.service;

import com.arjun.model.User;

public interface UserService {
	
	User saveUser(User user);
	Boolean verifyToken(String token);

}
