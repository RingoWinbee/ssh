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
@Table(name = "goods")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Goods {

	@Id
	@Column(name = "goodsId", updatable = false)
	@GeneratedValue()
	private int goodsId;

	@Column(name = "goodsName")
	private String goodsName;

	@Column(name = "goodsPrice")
	private Double goodsPrice;

	@Column(name = "goodsRealPrice")
	private Double goodsRealPrice;

	@Column(name = "goodsImg")
	private String goodsImg;

	@Column(name = "goodsInfo")
	private String goodsInfo;

	@Column(name = "goodsStock")
	private Double goodsStock;

	@Column(name = "goodsState")
	private int goodsState;

	/**
	 * 商品类型
	 */
	// 多对一对应category表里面的主码id
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId", unique = false)
	private Category category; // 关联产品类型那边

	// 将商品和购物车列表进行一对多关联
	@SuppressWarnings("deprecation")
	@OneToMany(targetEntity = ShopCarList.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsId")
	@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
	private Set<ShopCarList> shopCarList;

	@Column(name = "goodsDate")
	private Date goodsDate;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Double getGoodsRealPrice() {
		return goodsRealPrice;
	}

	public void setGoodsRealPrice(Double goodsRealPrice) {
		this.goodsRealPrice = goodsRealPrice;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Double getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Double goodsStock) {
		this.goodsStock = goodsStock;
	}

	public int getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(int goodsState) {
		this.goodsState = goodsState;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getGoodsDate() {
		return goodsDate;
	}

	public void setGoodsDate(Date goodsDate) {
		this.goodsDate = goodsDate;
	}

	public Set<ShopCarList> getShopCarList() {
		return shopCarList;
	}

	public void setShopCarList(Set<ShopCarList> shopCarList) {
		this.shopCarList = shopCarList;
	}
	
}
