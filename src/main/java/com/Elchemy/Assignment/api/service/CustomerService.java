package com.Elchemy.Assignment.api.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private void createSession(HttpServletRequest request, Customer customer) {
		HttpSession session = request.getSession(true);
		session.setAttribute("id", customer.getId());
		session.setAttribute("email", customer.getEmail());
		session.setAttribute("name", customer.getName());
	}
	
	public boolean validateCustomer(String email, String password, HttpServletRequest request) {
		Customer customer = customerRepo.getByEmail(email, password);
		boolean isValid = customer != null;
		if(isValid)
			createSession(request, customer);
		return isValid;
	}
}
