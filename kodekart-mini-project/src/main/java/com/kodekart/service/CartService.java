package com.kodekart.service;

import java.util.List;

import com.kodekart.model.Cart;

public interface CartService {
	
		boolean addToCart(int userId, int productId, int qty);

		List<Cart> getUserCart(int userId);

		boolean removeItem(int cartId);

		boolean clearCart(int userId);
}
