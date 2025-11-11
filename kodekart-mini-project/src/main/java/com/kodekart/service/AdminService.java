package com.kodekart.service;

import com.kodekart.model.Users;

public interface AdminService {
	Users login(String email, String password);
}
