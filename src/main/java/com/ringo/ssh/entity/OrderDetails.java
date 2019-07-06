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
@Table(name = "orderDetails")
@DynamicUpdate(true)
@DynamicInsert(true)
public class OrderDetails {
	
	@Id
	@Column(name = "orderDetailsListId",updatable=false)
	@GeneratedValue()
	private int orderDetailsListId;
	
	//多对一对应order表里面的主码orderId
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", unique = false)
	private Order order;
    
    //多对一对应goods表里面的主码goodsId
    @ManyToOne
    @JoinColumn(name = "goodsId", referencedColumnName = "goodsId", unique = false)
	private Goods goods;
    
    //商品数量
    @Column(name = "goodsCounts")
    private int goodsCount;
    
    @Column(name = "sumMoney")
    private Double sumMoney;

	public int getOrderDetailsListId() {
		return orderDetailsListId;
	}

	public void setOrderDetailsListId(int orderDetailsListId) {
		this.orderDetailsListId = orderDetailsListId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public Double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}
}
