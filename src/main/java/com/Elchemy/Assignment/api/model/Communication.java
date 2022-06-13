package com.Elchemy.Assignment.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "Communication")
public class Communication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long c_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Customer customer;
	
	@Column(name = "Conversation")
	private String conversation;
	
	@Column(name = "Time", nullable = false, updatable = false)
	@CreationTimestamp
	private Date time;
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public Date getTime() {
		return this.time;	
	}
	
	public String getConversation() {
		return this.conversation;	
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setConversation(String conversation) {
		this.conversation = conversation;	
	}
	
	
	public void setId(Customer customer) {
		this.customer = customer;
	}
	
	public Communication(Long c_id, Customer customer, String conversation, Date time) {
		super();
		this.c_id = c_id;
		this.customer = customer;
		this.conversation = conversation;
		this.time = time;
	}
	
	public Communication() {
		
	}
	
	@Override
	public String toString() {
		return time.toString()+ " : " + conversation;
	}
	

}
