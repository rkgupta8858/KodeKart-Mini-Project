package com.kodekart.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.kodekart.dao.OrderItemDao;

public class OrderItemDaoImpl implements OrderItemDao {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/kodekart";
	String userName = "root";
	String userPass = "root";
	String sql;
	Connection connection;
	PreparedStatement preparedStatement;

	@Override
	public boolean insert(int orderId, int productId, int quantity, double price) {

		String sql = "INSERT INTO order_items(order_id,product_id,quantity,price) VALUES(?,?,?,?)";

		try {

			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, quantity);
			preparedStatement.setDouble(4, price);

			return preparedStatement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
