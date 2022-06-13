package com.Elchemy.Assignment.api.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Elchemy.Assignment.api.dao.CommunicationRepository;
import com.Elchemy.Assignment.api.model.Communication;
import com.Elchemy.Assignment.api.model.Customer;

@Component
public class CommunicationService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	CommunicationRepository communicationRepository;
	
	public boolean save(Communication communication, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		communication.setId(entityManager.getReference(Customer.class, (Long) session.getAttribute("id")));
		Communication savedComm = communicationRepository.save(communication);
		return savedComm != null;
	}
	
	public List<Communication> getByUserId(Long userId) {
		return communicationRepository.findAll().stream().filter(c -> userId.equals(c.getCustomer().getId())).collect(Collectors.toList());
	}

}
