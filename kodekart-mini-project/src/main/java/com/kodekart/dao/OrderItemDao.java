package com.kodekart.dao;

import java.util.List;

import com.kodekart.model.OrderItemDetails;

public interface OrderItemDao {
	boolean insert(int orderId, int productId, int quantity, double price);

	List<OrderItemDetails> getItemsByOrderId(int orderId);

}
