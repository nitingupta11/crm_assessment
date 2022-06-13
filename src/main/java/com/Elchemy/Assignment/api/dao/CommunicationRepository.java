package com.Elchemy.Assignment.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Elchemy.Assignment.api.model.Communication;

public interface CommunicationRepository extends JpaRepository<Communication, Long> {
	
	@Query("select c from Communication c where c.id= ?1")
	List<Communication> getByUserId(Long userId);

}
