package com.kodekart.controller;

import java.util.List;
import java.util.Scanner;

import com.kodekart.model.Cart;
import com.kodekart.service.CartService;
import com.kodekart.service.impl.CartServiceImpl;
import com.kodekart.util.Session;

public class CartController {

    private CartService cartService = new CartServiceImpl();
    Scanner sc = new Scanner(System.in);

    public void addToCart() {
        if (Session.getUser() == null) {
            System.out.println("Please login first!");
            return;
        }

        int userId = Session.getUser().getId();
        System.out.print("Enter product id: ");
        int productId = sc.nextInt();

        System.out.print("Enter qty: ");
        int qty = sc.nextInt();

        boolean added = cartService.addToCart(userId, productId, qty);

        System.out.println(added ? "✅ Added to cart!" : "❌ Failed to add");
    }

    public void viewCart() {
        if (Session.getUser() == null) {
            System.out.println("Please login first!");
            return;
        }

        int userId = Session.getUser().getId();
        List<Cart> list = cartService.getUserCart(userId);

        if(list.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        System.out.println("===== Your Cart =====");
        list.forEach(System.out::println);
    }
}
