package com.demo.dao;

import java.util.List;

import com.demo.model.Customer;

public interface CustomerRepositoryCustom {

	List<Customer> findBy(String findBy, String value);
}
