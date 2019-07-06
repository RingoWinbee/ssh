package com.ringo.ssh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "order")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Order {

}
