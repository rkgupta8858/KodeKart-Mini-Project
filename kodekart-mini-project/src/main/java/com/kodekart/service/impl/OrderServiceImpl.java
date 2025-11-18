package com.kodekart.service.impl;

import java.util.ArrayList;
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
import com.kodekart.model.OrderItemDetails;
import com.kodekart.model.Orders;
import com.kodekart.model.Products;
import com.kodekart.service.OrderService;

public class OrderServiceImpl implements OrderService {
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
//	        System.out.println("Order placed successfully!");
//	        return true;
//	}

//	@Override
	public boolean placeOrder(int userId) {

		List<Cart> cartItems = cartDao.getUserCart(userId);

		if (cartItems.isEmpty()) {
			System.err.println("Cart is empty!");
			return false;
		}

		double totalAmount = 0;
		List<String> summary = new ArrayList<>();

		// STEP 1: Calculate total + prepare summary
		for (Cart c : cartItems) {
			Products p = adminDashDao.getProductById(c.getProductId());
			if (p == null) {
				System.err.println("Product not found: " + c.getProductId());
				return false;
			}

			double lineTotal = p.getPrice() * c.getQuantity();
			totalAmount += lineTotal;

			summary.add(p.getName() + "  (₹" + p.getPrice() + " × " + c.getQuantity() + " = ₹" + lineTotal + ")");
		}

		// STEP 2: Save order
		int orderId = orderDao.createOrder(userId, totalAmount);
		if (orderId <= 0) {
			System.err.println("Order not created");
			return false;
		}

		System.out.println("\nCreating Order... Order ID: " + orderId);

		// STEP 3: Insert order items + update stock
		for (Cart c : cartItems) {

			Products p = adminDashDao.getProductById(c.getProductId());
			int availableQty = p.getQuantity();
			int orderedQty = c.getQuantity();

			// STOCK VALIDATION: prevent ordering more than available
			if (availableQty < orderedQty) {
				System.err.println("Insufficient stock for product: " + p.getName() + " (Available: " + availableQty
						+ ", Required: " + orderedQty + ")");
				return false; // STOP the entire order
			}

			// Insert order item
			boolean saved = orderItemDao.insert(orderId, p.getId(), orderedQty, p.getPrice());
			if (!saved) {
				System.err.println("Failed to save order item: " + p.getId());
				return false;
			}

			// Update stock safely
			int newQty = availableQty - orderedQty;
			boolean updated = adminDashDao.updateQuantity(p.getId(), newQty);

			if (!updated) {
				System.err.println("Failed to update stock for product: " + p.getId());
				return false;
			}
		}

		// STEP 4: Clear Cart
		if (!cartDao.clearCart(userId)) {
			System.err.println("Failed to clear cart!");
			return false;
		}

		// STEP 5: Print Summary
		System.out.println("\n=====================================");
		System.out.println("          ORDER SUMMARY  ");
		System.out.println("=====================================");
		for (String s : summary) {
			System.out.println(s);
		}
		System.out.println("-------------------------------------");
		System.out.println("Final Amount: ₹" + totalAmount);
		System.out.println("=====================================\n");

		System.out.println("Order placed successfully!");
		return true;
	}

	@Override
	public List<Orders> getOrdersByUserId(int userId) {
		return orderDao.getOrdersByUserId(userId);
	}

	public List<OrderItemDetails> getOrderItems(int orderId) {
		return orderItemDao.getItemsByOrderId(orderId);
	}

}
