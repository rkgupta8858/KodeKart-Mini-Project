package com.kodekart.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kodekart.dao.AdminDashDao;
import com.kodekart.model.Products;

public class AdminDashDaoImpl implements AdminDashDao {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/kodekart";
	String userName = "root";
	String userPass = "root";
	String sql;

	Connection connection;
	PreparedStatement preparedStatement;

	@Override
	public boolean addProduct(Products products) {
		sql = "insert into products(name,category,price,quantity,description) values(?,?,?,?,?)";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, products.getName());
			preparedStatement.setString(2, products.getCategory());
			preparedStatement.setDouble(3, products.getPrice());
			preparedStatement.setInt(4, products.getQuantity());
			preparedStatement.setString(5, products.getDescription());

			return preparedStatement.executeUpdate() > 1;
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateProductByQuantity(String name, int quantity) {
		sql = "update products set quantity=? where name=? ";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, quantity);
			preparedStatement.setString(2, name);

			return preparedStatement.executeUpdate() > 0;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductByName(String name) {
		sql = "delete from products where name=?";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, name);

			return preparedStatement.executeUpdate() > 0;

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return false;

	}

	@Override
	public List<Products> getAllProduct() {
		List<Products> list = new ArrayList<Products>();
		sql = "select * from Products";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Products p = new Products(0, userName, sql, 0, 0, driver);
				p.setId(resultSet.getInt("id"));
				p.setName(resultSet.getString("name"));
				p.setCategory(resultSet.getString("category"));
				p.setPrice(resultSet.getDouble("price"));
				p.setQuantity(resultSet.getInt("quantity"));
				p.setDescription(resultSet.getString("description"));

				list.add(p);
			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

}
