package com.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.model.Customer;

public interface CustomerService {

	public Customer save(Customer customer);
	public int deleteById(String id);
	public List<Customer> findAll();
	public Page<Customer> findAllByPaging(int page);
	public List<Customer> findAllBySorting(String sortBy);
	public List<Customer> findByFirstNameContains(String name);
	public List<Customer> findBy(String findBy, String value);
	public Customer findById(String id);
	
	
}
