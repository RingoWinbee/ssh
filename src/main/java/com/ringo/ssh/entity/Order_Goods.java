package com.ringo.ssh.entity;

import java.util.Date;
import java.util.List;

public class Order_Goods {

	private int OrderId;
	
	private List<Goods> goodss;
	
	private List<Integer> goodsCount;

	
	private String consigneeName;
	
	private String orderAddress;
	
	private Date orderDate;
	
	private String orderPhone;
	
	private Double totalMoney;
	
	private int orderState;

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}


	public List<Goods> getGoodss() {
		return goodss;
	}

	public void setGoodss(List<Goods> goods) {
		this.goodss = goods;
	}


	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public List<Integer> getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(List<Integer> goodsCount) {
		this.goodsCount = goodsCount;
	}
	
}
