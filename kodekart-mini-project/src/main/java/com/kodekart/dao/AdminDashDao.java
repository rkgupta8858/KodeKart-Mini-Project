package com.kodekart.dao;

import java.util.List;

import com.kodekart.model.Products;

public interface AdminDashDao {

	boolean addProduct(Products products);

	boolean updateProductByQuantity(String name, int quantity);

	boolean deleteProductByName(String name);

	List<Products> getAllProduct();

	Products getProductById(int productId);

	boolean updateQuantity(int productId, int newQuantity);

}
