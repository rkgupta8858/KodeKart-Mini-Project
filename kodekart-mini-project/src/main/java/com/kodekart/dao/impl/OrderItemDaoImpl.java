package com.kodekart.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kodekart.dao.OrderItemDao;
import com.kodekart.model.OrderItemDetails;

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

	@Override
	public List<OrderItemDetails> getItemsByOrderId(int orderId) {

		List<OrderItemDetails> list = new ArrayList<>();

		String sql = "SELECT p.name AS productName, oi.quantity, oi.price " + "FROM order_items oi "
				+ "JOIN products p ON oi.product_id = p.id " + "WHERE oi.order_id = ?";

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, orderId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				list.add(new OrderItemDetails(rs.getString("productName"), rs.getInt("quantity"),
						rs.getDouble("price")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
