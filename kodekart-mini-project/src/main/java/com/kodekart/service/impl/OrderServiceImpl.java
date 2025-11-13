package com.kodekart.service.impl;

import java.util.List;

import com.kodekart.dao.AdminDashDao;
import com.kodekart.dao.CartDao;
import com.kodekart.dao.OrderDao;
import com.kodekart.dao.OrderItemDao;
import com.kodekart.dao.impl.AdminDashDaoImpl;
import com.kodekart.dao.impl.CartDaoImpl;
import com.kodekart.dao.impl.OrderDaoImpl;
import com.kodekart.dao.impl.OrderItemDaoImpl;
import com.kodekart.model.Cart;
import com.kodekart.model.Products;
import com.kodekart.service.OrderService;

public class OrderServiceImpl implements OrderService{
	private CartDao cartDao = new CartDaoImpl();
	private AdminDashDao adminDashDao = new AdminDashDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	
//	
//	@Override
//	public boolean placeOrder(int userId) {
//		 List<Cart> cartItems = cartDao.getUserCart(userId);
//
//	        if (cartItems.isEmpty()) {
//	            System.out.println("Cart is empty!");
//	            return false;
//	        }
//
//	        double totalAmount = 0;
//
//	        for (Cart c : cartItems) {
//	            Products p = adminDashDao.getProductById(c.getProductId());
//	            totalAmount += p.getPrice() * c.getQuantity();
//	        }
//
//	        int orderId = orderDao.createOrder(userId, totalAmount);
//
//	        if (orderId <= 0) return false;
//
//	        for (Cart c : cartItems) {
//
//	            Products p = adminDashDao.getProductById(c.getProductId());
//
//	            // Create order-items
//	            orderItemDao.insert(orderId, p.getId(), c.getQuantity(), p.getPrice());
//
//	            // Reduce stock
//	            adminDashDao.updateQuantity(p.getId(), p.getQuantity() - c.getQuantity());
//	        }
//
//	        // Clear cart
//	        cartDao.clearCart(userId);
//
//	        System.out.println("✅ Order placed successfully!");
//	        return true;
//	}
	@Override
	public boolean placeOrder(int userId) {

	    List<Cart> cartItems = cartDao.getUserCart(userId);

	    if (cartItems.isEmpty()) {
	        System.out.println("❌ Cart is empty!");
	        return false;
	    }

	    double totalAmount = 0;

	    for (Cart c : cartItems) {
	        Products p = adminDashDao.getProductById(c.getProductId());
	        if (p == null) {
	            System.out.println("❌ Product not found: " + c.getProductId());
	            return false;
	        }
	        totalAmount += p.getPrice() * c.getQuantity();
	    }

	    int orderId = orderDao.createOrder(userId, totalAmount);
	    if (orderId <= 0) {
	        System.out.println("❌ Order not created");
	        return false;
	    }

	    System.out.println("✅ Order created. ID: " + orderId);

	    // Insert order items & update quantity
	    for (Cart c : cartItems) {
	        Products p = adminDashDao.getProductById(c.getProductId());

	        boolean saved = orderItemDao.insert(orderId, p.getId(), c.getQuantity(), p.getPrice());
	        if (!saved) {
	            System.out.println("❌ Failed to save order item: " + p.getId());
	            return false;
	        }

	        boolean updated = adminDashDao.updateQuantity(p.getId(), p.getQuantity() - c.getQuantity());
	        if (!updated) {
	            System.out.println("❌ Failed to update stock for product: " + p.getId());
	            return false;
	        }
	    }

	    boolean cleared = cartDao.clearCart(userId);
	    if (!cleared) {
	        System.out.println("❌ Failed to clear cart");
	        return false;
	    }

	    System.out.println("✅ Order placed successfully!");
	    return true;
	}


}
