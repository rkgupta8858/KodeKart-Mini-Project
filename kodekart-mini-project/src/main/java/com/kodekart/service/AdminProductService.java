package com.kodekart.service;

import java.util.List;

import com.kodekart.model.Products;

public interface AdminProductService {
	boolean addProduct(Products products);
	boolean updateProductQuantity(String name, int quantity);
	boolean deleteProductByName(String name);
	List<Products> getAllProduct();
}
