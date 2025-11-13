package com.kodekart.dao;

public interface OrderDao {
	int createOrder(int userId, double totalAmount);
}
