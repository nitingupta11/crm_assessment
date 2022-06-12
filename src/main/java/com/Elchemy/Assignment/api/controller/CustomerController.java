package com.Elchemy.Assignment.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.Elchemy.Assignment.api.model.Customer;
import com.Elchemy.Assignment.api.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/signup")
	public ModelAndView createCustomer(@ModelAttribute Customer customer){
		Customer customerWithId = customerService.saveCustomer(customer);
		RedirectView rv = new RedirectView();
		rv.setUrl("/customer/home");
		ModelAndView mv = new ModelAndView(rv);
		mv.addObject("customer", customerWithId);
		return mv;
	}
	
	@PostMapping("/loginRequest")
	public ModelAndView login(@RequestParam String email, @RequestParam String psw, HttpServletRequest request) {
		// validation for email
		boolean isValidated = customerService.validateCustomer(email, psw);
		
		// if true then redirect to home
		RedirectView rv = new RedirectView();
		if(isValidated) {
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			session.setAttribute("password", psw);
			rv.setUrl("/customer/home");
	        
		} else {
			rv.setUrl("/");
		}
    
		return new ModelAndView(rv);
      
	}
	
	@GetMapping("/home")
	public ModelAndView home(HttpServletRequest request) {
		HttpSession session =request.getSession(false);
		if(session == null || ((String) session.getAttribute("email")) == null) {
			RedirectView rv = new RedirectView("/");
	        return new ModelAndView(rv);
		}
		return new ModelAndView("home");
	}
	
	@GetMapping("/signup")
	public ModelAndView singup() {	 
		return new ModelAndView("signup");
	}
	
	@GetMapping("/logout") 
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null)
		session.invalidate();
		RedirectView rv = new RedirectView("/");
        return new ModelAndView(rv);
	}
	
	
}
