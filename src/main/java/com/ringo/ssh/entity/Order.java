package com.ringo.ssh.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "orders")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Order {

	@Id
	@Column(name = "orderId", updatable = false)
	@GeneratedValue()
	private int orderId;

	@Column(name = "orderDate")
	private Date orderDate;

	@Column(name = "totalMoney")
	private Double totalMoney;

	@Column(name = "consigneeName")
	private String consigneeName;

	@Column(name = "orderPhone")
	private String orderPhone;

	@Column(name = "orderAddress")
	private String orderAddress;

	@Column(name = "orderState")
	private int orderState;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId", unique = false)
	private User users;

	// 将订单和订单详情列表进行一对多关联
	@SuppressWarnings("deprecation")
	@OneToMany(targetEntity = OrderDetails.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private Set<OrderDetails> orderDetails;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
