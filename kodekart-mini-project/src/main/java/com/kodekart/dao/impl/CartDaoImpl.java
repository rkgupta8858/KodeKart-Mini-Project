package com.kodekart.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kodekart.dao.CartDao;
import com.kodekart.model.Cart;

public class CartDaoImpl implements CartDao {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/kodekart";
	String userName = "root";
	String userPass = "root";
	String sql;
	Connection connection;
	PreparedStatement preparedStatement;

	@Override
	public boolean addToCart(int userId, int productId, int qty) {
		try {
			Class.forName(driver);
			sql = "INSERT INTO cart(user_id,product_id,quantity) VALUES(?,?,?)";
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, qty);

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Cart> getUserCart(int userId) {
		List<Cart> list = new ArrayList<>();
		sql = "SELECT * FROM cart WHERE user_id=?";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				list.add(new Cart(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("product_id"),
						rs.getInt("quantity")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean removeItem(int cartId) {
		sql = "DELETE FROM cart WHERE id=?";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, cartId);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean clearCart(int userId) {
		sql = "DELETE FROM cart WHERE user_id=?";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, userId);
			return preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
