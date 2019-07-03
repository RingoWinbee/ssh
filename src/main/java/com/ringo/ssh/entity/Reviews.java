package com.ringo.ssh.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "reviews")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Reviews {
	
	@Id
	@Column(name = "reviewsId",updatable=false)
	@GeneratedValue()
	private int reviewsId;
	
	//这个是错的
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="userId",unique=true)
//	private User user;
	
	/**
     * 评价对应的商品
    */
	//多对一对应goods表里面的主码id
    @ManyToOne
    @JoinColumn(name = "goodsId", referencedColumnName = "goodsId", unique = false)
    private Goods goods;   //关联产品类型那边
    
    @Column(name = "reviewsInfo")
    private String reviewsInfo;
    
    @Column(name = "reviewsDate")
    private Date reviewsDate;

	public int getReviewsId() {
		return reviewsId;
	}

	public void setReviewsId(int reviewsId) {
		this.reviewsId = reviewsId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getReviewsInfo() {
		return reviewsInfo;
	}

	public void setReviewsInfo(String reviewsInfo) {
		this.reviewsInfo = reviewsInfo;
	}

	public Date getReviewsDate() {
		return reviewsDate;
	}

	public void setReviewsDate(Date reviewsDate) {
		this.reviewsDate = reviewsDate;
	}
    
    
}
