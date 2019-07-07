package com.ringo.ssh.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@OneToOne
	@JoinColumn(name = "orderDetailsId", referencedColumnName = "orderDetailsId", unique = true)
	private OrderDetails orderdetails;
	
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

	public OrderDetails getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(OrderDetails orderdetails) {
		this.orderdetails = orderdetails;
	}
    
}
