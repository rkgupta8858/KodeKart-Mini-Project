package com.kodekart.service.impl;

import com.kodekart.dao.UserDao;
import com.kodekart.dao.impl.UserDaoImpl;
import com.kodekart.model.Users;
import com.kodekart.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private UserDao userDao = new UserDaoImpl();

	@Override
	public Users login(String email, String password) {
		return userDao.login(email, password);

	}
}
