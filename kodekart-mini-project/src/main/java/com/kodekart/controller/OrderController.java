package com.kodekart.controller;

import java.util.List;

import com.kodekart.model.OrderItemDetails;
import com.kodekart.model.Orders;
import com.kodekart.service.OrderService;
import com.kodekart.service.impl.OrderServiceImpl;
import com.kodekart.util.Session;

public class OrderController {

	private OrderService orderService = new OrderServiceImpl();

	public void placeOrder() {

		if (Session.getUser() == null) {
			System.out.println("Login first!");
			return;
		}

		int userId = Session.getUser().getId();
		boolean result = orderService.placeOrder(userId);

		System.out.println(result ? "Order placed!" : "Failed!");
	}

	public void viewOrderHistory() {

		if (Session.getUser() == null) {
			System.out.println("Please login first!");
			return;
		}

		int userId = Session.getUser().getId();
		List<Orders> history = orderService.getOrdersByUserId(userId);

		if (history.isEmpty()) {
			System.out.println("📭 No orders found!");
			return;
		}

		System.out.println("\n========= YOUR ORDER HISTORY =========");

		for (Orders o : history) {

			System.out.println("\nOrder ID : " + o.getId());
			System.out.println("Order Date : " + o.getOrderdate());
			System.out.println("Total Amount : ₹" + o.getTotalAmount());
			System.out.println("----------- ITEMS --------------");

			List<OrderItemDetails> items = orderService.getOrderItems(o.getId());

			for (OrderItemDetails item : items) {
				double lineTotal = item.getPrice() * item.getQuantity();
				System.out.println("📦 " + item.getProductName() + " | Qty: " + item.getQuantity() + " | Price: ₹"
						+ item.getPrice() + " | Total: ₹" + lineTotal);
			}

			System.out.println("---------------------------------------");
		}
	}

}
