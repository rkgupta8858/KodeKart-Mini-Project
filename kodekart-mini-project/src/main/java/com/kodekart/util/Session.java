package com.kodekart.util;

import com.kodekart.model.Users;

public class Session {
	private static Users loggedInUser;

	public static Users getUser() {
		return loggedInUser;
	}

	public static void setUser(Users users) {
		loggedInUser = users;
	}

	public static void clear() {
		loggedInUser = null;
	}
}
