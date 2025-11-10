package com.kodekart.model;

import java.sql.Timestamp;

public class Orders {
	private int id;
	private int userIid;
	private Timestamp orderdate;
	private double totalAmount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserIid() {
		return userIid;
	}

	public void setUserIid(int userIid) {
		this.userIid = userIid;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
