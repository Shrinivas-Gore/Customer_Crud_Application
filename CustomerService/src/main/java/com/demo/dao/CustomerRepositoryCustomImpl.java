package com.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

	
	@Autowired
    private EntityManager entityManager;

	@Override
	public List<Customer> findBy(String findBy, String value) {
		String sql = "SELECT * FROM customer WHERE " + findBy + " REGEXP :value";
        Query query = entityManager.createNativeQuery(sql, Customer.class);
        query.setParameter("value", value);
        return query.getResultList();
	}

}
