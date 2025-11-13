package com.kodekart.dao;

import java.util.List;
import com.kodekart.model.Cart;

public interface CartDao {
	
	boolean addToCart(int userId, int productId, int qty);

	List<Cart> getUserCart(int userId);

	boolean removeItem(int cartId);

	boolean clearCart(int userId);
}
