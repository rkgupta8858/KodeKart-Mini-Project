package com.kodekart.user.dashboard;

import java.util.Scanner;

import com.kodekart.controller.AdminDashAddController;
import com.kodekart.controller.CartController;
import com.kodekart.controller.OrderController;

public class UserMenuDashboard {
	public void showUserMenu() {
		AdminDashAddController controller = new AdminDashAddController();
		CartController cartController = new CartController();
		OrderController orderController = new OrderController();
		boolean exist = false;
		Scanner scanner = new Scanner(System.in);

		while (!exist) {
			System.out.println("\n========== User Menu ==========");
			System.out.println("1 : View Product");
			System.out.println("2 : Add To Cart");
			System.out.println("3 : View Cart");
			System.out.println("4 : Place Order");
			System.out.println("5 : View Order History");
			System.out.println("6 : Logout");

			System.out.print("Enter selection : ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				controller.getAllProduct();
				break;
			case 2:
				cartController.addToCart();
				break;
			case 3:
				cartController.viewCart();
				break;
			case 4:
				orderController.placeOrder();
				break;
			case 5:
				
				break;
			case 6:
				
				break;

			default:
				System.err.println("Please enter valid Selection from 1 to 6	");
				break;
			}
		}
	}
}
