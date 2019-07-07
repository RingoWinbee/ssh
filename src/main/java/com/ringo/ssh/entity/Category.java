package com.ringo.ssh.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "category")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Category {
	
	@Id
	@Column(name = "categoryId",updatable=false)
	@GeneratedValue()
	private int categoryId;
	
	@Column(name = "categoryName")
	private String categoryName;
	
	//将商品和商品类别进行一对多关联
		@SuppressWarnings("deprecation")
		@OneToMany(targetEntity = Goods.class,fetch=FetchType.LAZY)
		@JoinColumn(name="categoryId")
		@Cascade({CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN})
	private Set<Goods> goods;

	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
