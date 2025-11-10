package com.kodekart.dao;

import com.kodekart.model.Users;

public interface UserDao {
	boolean register(Users users);

	Users login(String email, String password);

	Users getByEmail(String email);
}
