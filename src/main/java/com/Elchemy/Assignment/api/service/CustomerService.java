package com.Elchemy.Assignment.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.Elchemy.Assignment.api.dao.CustomerRepository;
import com.Elchemy.Assignment.api.model.Customer;

@Component
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	public boolean validateCustomer(String email, String password) {
		Customer customer = customerRepo.getByEmail(email, password);
		return customer != null;
	}
}
