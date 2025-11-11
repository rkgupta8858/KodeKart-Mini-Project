package com.kodekart.admin.dashboard;

import java.util.Scanner;

import com.kodekart.controller.AdminDashAddController;

public class AdminDashboard {
   public void adminDash() {
	   AdminDashAddController controller = new AdminDashAddController();
	   Scanner scanner = new Scanner(System.in);

		boolean exit = false;

		while (!exit) {
			System.out.println("1 : Add Product");
			System.out.println("2 : Update Product");
			System.out.println("3 : Delete Product");
			System.out.println("4 : View All Product");
			System.out.println("5 : Exit");

			System.out.print("Enter Selection : ");

			int selection = scanner.nextInt();

			switch (selection) {
			case 1:
				controller.addProduct();
			case 2:
				controller.updateProduct();
				break;
			case 3:
				controller.deleteProductByName();
				break;
			case 4:
				controller.getAllProduct();
				break;
			case 5:
				exit = true;
				System.out.println("Exiting....");
				break;

			default:
				System.err.println("Invalid Choice..");
				break;
			}

		}
   } 
}
