package com.kodekart.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.kodekart.dao.UserDao;
import com.kodekart.model.Users;

public class UserDaoImpl implements UserDao {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/kodekart";
	String userName = "root";
	String userPass = "root";
	String sql;

	Connection connection;

	@Override
	public boolean register(Users users) {
		try {
			Class.forName(driver);
			sql = "insert into users(name,email,phone,password) values(?,?,?,?)";
			connection = DriverManager.getConnection(url, userName, userPass);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, users.getName());
			preparedStatement.setString(2, users.getEmail());
			preparedStatement.setString(3, users.getPhone());
			preparedStatement.setString(4, users.getPassword());

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (Exception e) {

			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Users login(String email, String password) {
		try {
			Class.forName(driver);
			sql = "select * from users where email=? and password =?";
			connection = DriverManager.getConnection(url, userName, userPass);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return new Users(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone"),rs.getString("password"));
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Users getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
