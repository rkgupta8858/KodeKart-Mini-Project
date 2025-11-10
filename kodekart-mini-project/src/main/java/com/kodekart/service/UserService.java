package com.kodekart.service;

import com.kodekart.model.Users;

public interface UserService {
	boolean register(Users users);

	Users login(String email, String password);
}
