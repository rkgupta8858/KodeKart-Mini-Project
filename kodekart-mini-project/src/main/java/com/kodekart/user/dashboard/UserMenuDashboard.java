package com.kodekart.user.dashboard;

import java.util.Scanner;

import com.kodekart.controller.AdminDashAddController;
import com.kodekart.controller.CartController;
import com.kodekart.controller.OrderController;
import com.kodekart.util.Session;

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
			System.out.println("3 : Remove Cart Item");
			System.out.println("4 : Clear Cart");
			System.out.println("5 : View Cart");
			System.out.println("6 : Place Order");
			System.out.println("7 : View Order History");
			System.out.println("8 : Logout");

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
				cartController.removeItem();
				break;
			case 4:
				cartController.clearCart();
				break;
			case 5:
				cartController.viewCart();
				break;
			case 6:
				orderController.placeOrder();
				break;
			case 7:
				orderController.viewOrderHistory();
				break;
			case 8:
				Session.clear();
				System.err.println("Logged out successfully!");
				exist = true;
				break;

			default:
				System.err.println("Please enter valid Selection from 1 to 6	");
				break;
			}
		}
	}
}
