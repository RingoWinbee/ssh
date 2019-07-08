package com.ringo.ssh.entity;

public class ShopCarList_Goods {
	
	private int ShopCarListId;
	
	private int goodsId;
	
	private String goodsName;
	
	private Double goodsPtice;
	
	private int goodsCount;
	
	private String gooodsImg;

	public int getShopCarListId() {
		return ShopCarListId;
	}

	public void setShopCarListId(int shopCarListId) {
		ShopCarListId = shopCarListId;
	}

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

	public Double getGoodsPtice() {
		return goodsPtice;
	}

	public void setGoodsPtice(Double goodsPtice) {
		this.goodsPtice = goodsPtice;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGooodsImg() {
		return gooodsImg;
	}

	public void setGooodsImg(String gooodsImg) {
		this.gooodsImg = gooodsImg;
	}
	
}
