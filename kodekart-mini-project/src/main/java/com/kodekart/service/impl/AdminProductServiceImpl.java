package com.kodekart.service.impl;

import java.util.List;

import com.kodekart.dao.AdminDashDao;
import com.kodekart.dao.impl.AdminDashDaoImpl;
import com.kodekart.model.Products;
import com.kodekart.service.AdminProductService;

public class AdminProductServiceImpl implements AdminProductService{

	private AdminDashDao adminDashDao;
	
	public AdminProductServiceImpl() {
		this.adminDashDao =new AdminDashDaoImpl(); 
	}
	@Override
	public boolean addProduct(Products products) {
		
		return adminDashDao.addProduct(products);
	}
	@Override
	public boolean updateProductQuantity(String name, int quantity) {
		
		return adminDashDao.updateProductByQuantity(name,quantity);
	}
	@Override
	public boolean deleteProductByName(String name) {
		
		return adminDashDao.deleteProductByName(name);
	}
	@Override
	public List getAllProduct() {
		
		return adminDashDao.getAllProduct();
	}


}
