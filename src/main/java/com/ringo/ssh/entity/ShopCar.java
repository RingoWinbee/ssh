package com.ringo.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "shopCar")
@DynamicUpdate(true)
@DynamicInsert(true)
public class ShopCar {
	
	@Id
	@Column(name = "carId",updatable=false)
	@GeneratedValue()
	private int carId;
	
	@ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "userId", unique = false)
	private User users;
	
	@Column(name="goodsCounts")
	private int goodsCount;

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}


	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
}
