package com.kodekart.service.impl;

import java.util.List;

import com.kodekart.dao.CartDao;
import com.kodekart.dao.impl.CartDaoImpl;
import com.kodekart.model.Cart;
import com.kodekart.service.CartService;

public class CartServiceImpl implements CartService {
	 private CartDao cartDao = new CartDaoImpl();

	 @Override
	    public boolean addToCart(int userId, int productId, int qty) {
	        return cartDao.addToCart(userId, productId, qty);
	    }

	    @Override
	    public List<Cart> getUserCart(int userId) {
	        return cartDao.getUserCart(userId);
	    }

	    @Override
	    public boolean removeItem(int cartId) {
	        return cartDao.removeItem(cartId);
	    }

	    @Override
	    public boolean clearCart(int userId) {
	        return cartDao.clearCart(userId);
	    }

}
