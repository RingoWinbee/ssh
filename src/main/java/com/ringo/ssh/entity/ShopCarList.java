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
@Table(name = "shopCarList")
@DynamicUpdate(true)
@DynamicInsert(true)
public class ShopCarList {
	
	@Id
	@Column(name = "shopCarListId",updatable=false)
	@GeneratedValue()
	private int shopCarListId;
	
	//多对一对应ShopCar表里面的主码carId
    @ManyToOne
    @JoinColumn(name = "carId", referencedColumnName = "carId", unique = false)
	private ShopCar shopCar;
    
    //多对一对应goods表里面的主码goodsId
    @ManyToOne
    @JoinColumn(name = "goodsId", referencedColumnName = "goodsId", unique = true)
	private Goods goods;
    
    //商品数量
    @Column(name = "goodsCounts")
    private int goodsCount;

	public int getShopCarListId() {
		return shopCarListId;
	}

	public void setShopCarListId(int shopCarListId) {
		this.shopCarListId = shopCarListId;
	}

	public ShopCar getShopCar() {
		return shopCar;
	}

	public void setShopCar(ShopCar shopCar) {
		this.shopCar = shopCar;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
    
}
