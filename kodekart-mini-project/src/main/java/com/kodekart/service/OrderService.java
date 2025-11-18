package com.kodekart.service;

import java.util.List;

import com.kodekart.model.OrderItemDetails;
import com.kodekart.model.Orders;

public interface OrderService {
	boolean placeOrder(int userId);

	List<Orders> getOrdersByUserId(int userId);
	
	List<OrderItemDetails> getOrderItems(int orderId);
}
