package com.Elchemy.Assignment.api.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.Elchemy.Assignment.api.model.Communication;
import com.Elchemy.Assignment.api.model.Customer;
import com.Elchemy.Assignment.api.service.CommunicationService;
import com.Elchemy.Assignment.api.service.CustomerService;

@Controller
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CommunicationService communicationService;
	
	@PostMapping("/customer/signup")
	public ModelAndView createCustomer(@ModelAttribute Customer customer){
		Customer customerWithId = customerService.saveCustomer(customer);
		RedirectView rv = new RedirectView();
		rv.setUrl("/customer/home");
		ModelAndView mv = new ModelAndView(rv);
		mv.addObject("customer", customerWithId);
		return mv;
	}
	
	@PostMapping("/customer/loginRequest")
	public ModelAndView login(@RequestParam String email, @RequestParam String psw, HttpServletRequest request) {
		// validation for email
		boolean isValidated = customerService.validateCustomer(email, psw, request);
		
		// if true then redirect to home
		RedirectView rv = new RedirectView();
		if(isValidated) {
			rv.setUrl("/customer/home");
	        
		} else {
			rv.setUrl("/?message=Invalid%20Credentials");
		}
    
		return new ModelAndView(rv);
      
	}
	
	@GetMapping("/customer/home")
	public ModelAndView home(HttpServletRequest request) {
		HttpSession session =request.getSession(false);
		if(session == null || ((String) session.getAttribute("email")) == null) {
			RedirectView rv = new RedirectView("/");
	        return new ModelAndView(rv);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("name",session.getAttribute("name"));
		map.put("email",session.getAttribute("email"));
		List<Communication> communications = communicationService.getByUserId((Long) session.getAttribute("id"));
		map.put("communications", communications);
		return new ModelAndView("home", map);
	}
	
	@GetMapping("/customer/signup")
	public ModelAndView singup() {	 
		return new ModelAndView("signup");
	}
	
	@GetMapping("/customer/logout") 
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null)
		session.invalidate();
		RedirectView rv = new RedirectView("/?message=Successful%20Logout");
        return new ModelAndView(rv);
	}
	
	@GetMapping("/") 
	public ModelAndView login(@RequestParam(required = false, defaultValue = " ") String message, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			RedirectView rv = new RedirectView("/customer/home");
	        return new ModelAndView(rv);
		}
		Map<String, String> map = new HashMap<>();
		map.put("message", message);
        return new ModelAndView("login", map);
	}
	
	@PostMapping("/customer/commLog")
	public ModelAndView logComm(@RequestBody Communication communication, HttpServletRequest request) {
		communicationService.save(communication, request);
		RedirectView rv = new RedirectView("/customer/home");
        return new ModelAndView(rv);
	}
	
}
