package com.Elchemy.Assignment.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Table(name = "Customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Communication> cummunications;
	
	@Column(name = "Name", nullable = false)
	private String name;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Email", unique = true)
	private String email;
	
	@Column(name = "ContactNumber", unique = true)
	private long contactNumber;
	
	@Column(name = "GST", unique = true)
	private String gst;
	
	@Column(name = "Password")
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Customer() {
		
	}

	public Customer(long id, String name, String address, String email, long contactNumber, String gst,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.contactNumber = contactNumber;
		this.gst = gst;
		this.password = password;
	}
	
	public Customer(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
	
	


}
