package com.kodekart.main;

import java.util.Scanner;

import com.kodekart.controller.UserController;

public class App {
	public static void main(String[] args) {
		System.out.println("********--> Welcome to MINALkart <--********");
		UserController controller = new UserController();

		Scanner scanner = new Scanner(System.in);

		boolean exit = false;

		while (!exit) {
			System.out.println("1 : Register");
			System.out.println("2 : Login");
			System.out.println("3 : Exit");

			System.out.print("Enter Selection : ");

			int selection = scanner.nextInt();

			switch (selection) {
			case 1:
				controller.register();
				break;
			case 2:
				controller.login();
				break;
			case 3:
				exit = true;
				System.out.println("Exiting....");
				break;

			default:
				System.out.println("Invalid Choice..");
				break;
			}

		}
		scanner.close();
	}
}
