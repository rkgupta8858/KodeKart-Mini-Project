package com.kodekart.controller;

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
	
	
}
