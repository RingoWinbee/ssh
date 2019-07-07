package com.ringo.ssh.entity;

import java.util.Date;

public class Reviews_User {
	
	private String userName;
	
	private String reviewsInfo;
	
	private Date reviewsDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
