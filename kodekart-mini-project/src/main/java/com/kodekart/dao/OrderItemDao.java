package com.kodekart.dao;

public interface OrderItemDao {
	boolean insert(int orderId, int productId, int quantity, double price);
}
