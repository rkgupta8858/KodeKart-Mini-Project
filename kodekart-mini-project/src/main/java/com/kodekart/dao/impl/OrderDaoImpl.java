package com.kodekart.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kodekart.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/kodekart";
	String userName = "root";
	String userPass = "root";
	String sql;

	Connection connection;
	PreparedStatement preparedStatement;

	@Override
	public int createOrder(int userId, double totalAmount) {
		try {
			sql = "INSERT INTO orders(user_id,total_amount) VALUES(?,?)";

			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, userPass);
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, userId);
			preparedStatement.setDouble(2, totalAmount);

			int row = preparedStatement.executeUpdate();

			if (row > 0) {
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					return rs.getInt(1);
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
