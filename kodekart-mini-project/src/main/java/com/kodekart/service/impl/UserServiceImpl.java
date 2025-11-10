package com.kodekart.service.impl;

import com.kodekart.dao.UserDao;
import com.kodekart.dao.impl.UserDaoImpl;
import com.kodekart.model.Users;
import com.kodekart.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public boolean register(Users users) {
		Users exists = userDao.getByEmail(users.getEmail());
		if (exists!=null) {
			return false;
		}
		return userDao.register(users);
	}

	@Override
	public Users login(String email, String password) {
		return userDao.login(email, password);
		
	}

}
