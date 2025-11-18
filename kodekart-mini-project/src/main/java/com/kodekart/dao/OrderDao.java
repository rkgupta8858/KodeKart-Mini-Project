package com.kodekart.dao;

import java.util.List;

import com.kodekart.model.Orders;

public interface OrderDao {
	int createOrder(int userId, double totalAmount);
	List<Orders> getOrdersByUserId(int userId);
}
