package com.ringo.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//@Entity
//@Table(name="person")
//public class Person {
//	
//	private String id;
//	
//	private String name;
//	
//	private String idCard;
//	
//	private String phone;
//	
//	private String address;
//	
//	@Id
//	@Column(name="id",nullable=false,length=32,unique=true)
//	@GenericGenerator(name="generator",strategy="uuid.hex")
//	@GeneratedValue(generator="")
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//	
//	@Column(name="name",nullable=false,length=32)
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	@Column(name="idCard",nullable=false,length=32)
//	public String getIdCard() {
//		return idCard;
//	}
//
//	public void setIdCard(String idCard) {
//		this.idCard = idCard;
//	}
//
//	@Column(name="phone",nullable=false,length=32)
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	@Column(name="adress",nullable=false,length=32)
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//}

@Entity
@Table(name = "person")
public class Person {

	@Id
	@Column(name = "id")
	@GeneratedValue()
	private int id;

	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;

	@Column(name = "sex")
	private String sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}