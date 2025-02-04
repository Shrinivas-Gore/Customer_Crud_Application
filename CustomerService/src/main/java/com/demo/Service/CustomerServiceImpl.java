package com.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.dao.CustomerDao;
import com.demo.exception.CustomerNotFoundException;
import com.demo.model.Customer;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;
	
	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public Customer save(Customer customer) {
		return customerDao.save(customer);
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		int check = customerDao.deleteByCustomerId(id);
		if(check != 0)
			return check;
		else
			throw new CustomerNotFoundException("Enter valid Customer Id");
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public Page<Customer> findAllByPaging(int page) {
		PageRequest of = PageRequest.of(page, 10);
		return customerDao.findAll(of);
	}

	@Override
	public List<Customer> findAllBySorting(String sortBy) {
		Sort sorter = Sort.by(sortBy);
		return customerDao.findAll(sorter);
	}

	@Override
	public List<Customer> findByFirstNameContains(String name) {
		return customerDao.findByFirstNameContains(name);
	}

	@Override
	public List<Customer> findBy(String findBy, String value) {
		return customerDao.findBy(findBy, value);
	}

	@Override
	public Customer findById(String id) {
		return customerDao.findById(id).get();
	}
	
}
