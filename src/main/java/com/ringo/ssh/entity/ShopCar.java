package com.ringo.ssh.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "shopCar")
@DynamicUpdate(true)
@DynamicInsert(true)
public class ShopCar {

	@Id
	@Column(name = "carId", updatable = false)
	@GeneratedValue()
	private int carId;

	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId", unique = true)
	private User users;

	// 将购物车和购物车列表进行一对多关联
	@SuppressWarnings("deprecation")
	@OneToMany(targetEntity = ShopCarList.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "carId")
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
	private Set<ShopCarList> shopCarList;
	
	@Column(name = "goodsCounts")
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

	public Set<ShopCarList> getShopCarList() {
		return shopCarList;
	}

	public void setShopCarList(Set<ShopCarList> shopCarList) {
		this.shopCarList = shopCarList;
	}
	
}
