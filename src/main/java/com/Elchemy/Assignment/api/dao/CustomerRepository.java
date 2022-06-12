package com.Elchemy.Assignment.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Elchemy.Assignment.api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("select customer from Customer customer where customer.email = ?1 and customer.password = ?2")
	public Customer getByEmail(String email, String password);

}
