package com.kodekart.controller;

import java.util.Scanner;

import com.kodekart.admin.dashboard.AdminDashboard;
import com.kodekart.model.Users;
import com.kodekart.service.AdminService;
import com.kodekart.service.UserService;
import com.kodekart.service.impl.AdminServiceImpl;
import com.kodekart.service.impl.UserServiceImpl;
import com.kodekart.user.dashboard.UserMenuDashboard;
import com.kodekart.util.Session;

public class UserController {

	private Scanner scanner = new Scanner(System.in);
	private UserService userService = new UserServiceImpl();
	private AdminService adminService = new AdminServiceImpl();
	private UserMenuDashboard userMenuDashboard = new UserMenuDashboard();

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
		System.out.println("\n---- User Login ----");

		System.out.print("Enter Email: ");
		String email = scanner.nextLine();

		System.out.print("Enter Password: ");
		String pass = scanner.nextLine();

		Users u = userService.login(email, pass);

		if (u != null) {
			System.out.println("User Login Successful!.. Welcome " + u.getName());
			Session.setUser(u);
			userMenuDashboard.showUserMenu();
		} else {
			System.err.println("Invalid Login Details...");
		}

	}

	public void adminLogin() {
		AdminDashboard adminDashboard = new AdminDashboard();

		System.out.println("\n---- Admin Login ----");

		System.out.print("Enter Email: ");
		String email = scanner.nextLine();

		System.out.print("Enter Password: ");
		String pass = scanner.nextLine();

		Users u = adminService.login(email, pass);

		if (u != null) {
			System.out.println("Admin Login Successful!.. Welcome " + u.getName());
			
			adminDashboard.adminDash();
		} else {
			System.err.println("Invalid Login Details...");
		}

	}
}
