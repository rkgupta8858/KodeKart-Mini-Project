package com.kodekart.controller;

import java.util.Scanner;

import com.kodekart.model.Users;
import com.kodekart.service.UserService;
import com.kodekart.service.impl.UserServiceImpl;

public class UserController {

	private Scanner scanner = new Scanner(System.in);
	private UserService userService = new UserServiceImpl();

	public void register() {
		System.out.println("\n------User Registration------");

		System.out.print("Enter Name : ");
		String name = scanner.nextLine();

		System.out.print("Enter Email : ");
		String email = scanner.nextLine();

		System.out.print("Enter Phone : ");
		String phone = scanner.nextLine();

		System.out.print("Enter Password : ");
		String password = scanner.nextLine();

		Users users = new Users(0, name, email, phone, password);

		boolean registered = userService.register(users);
		if (registered) {
			System.out.println("Registration Sucessful!...");
		} else {
			System.err.println("Email already exist!...");
		}

	}

	public void login() {
		System.out.println("\n---- Login ----");

		System.out.print("Enter Email: ");
		String email = scanner.nextLine();

		System.out.print("Enter Password: ");
		String pass = scanner.nextLine();

		Users u = userService.login(email, pass);

		if (u != null) {
			System.out.println("Login Successful!.. Welcome " + u.getName());
		} else {
			System.err.println("Invalid Login Details...");
		}

	}
}
