package com.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, String>, CustomerRepositoryCustom  {

	public Page<Customer> findAll(Pageable pageable);
	public List<Customer> findAll(Sort sort);
	public List<Customer> findByFirstNameContains(String name);
	public int deleteByCustomerId(String id);
	
}
