package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Service.CustomerService;
import com.demo.model.Customer;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	
	//search api
	@GetMapping(value = "/search")
	public ResponseEntity<List<Customer>> searchCustomer(
			@RequestParam(value = "searchField", defaultValue = "first_name") String findBy,
			@RequestParam("searchTerm") String param) {
		System.out.println(param);
		return new ResponseEntity<>(customerService.findBy(findBy, param), HttpStatus.OK);
	}

	//iterate all customer api
	@GetMapping("/findAll")
	public ResponseEntity<List<Customer>> findAll() {
		return new ResponseEntity<List<Customer>>(customerService.findAll(), HttpStatus.OK);
	}

	
	//new customer adding api
	@PostMapping("/add")
	public ResponseEntity<String> save(@RequestBody Customer customer) {
		try {
			System.out.println(customer);
			customerService.save(customer);
			return new ResponseEntity<String>("Customer registered sucessfully", HttpStatus.OK);
		} catch (RuntimeException e) {
			throw new RuntimeException("Registration Failed");
		}
	}

	
	//update api
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Customer customer) {
		try {
			customerService.save(customer);
			return new ResponseEntity<String>("Customer registered sucessfully", HttpStatus.CREATED);
		} catch (RuntimeException e) {
			throw new RuntimeException("Registration Failed");
		}
	}

	
	//delete by id api
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id) {
		int check = customerService.deleteById(id);
		if (check > 0)
			return new ResponseEntity<String>("deleted Sucessfully", HttpStatus.OK);
		else
			throw new RuntimeException("Invalid id");
	}

	
	//find customer on selected page api
	@GetMapping("/findByPage")
	public ResponseEntity<List<Customer>> findByPageing(@RequestParam(value = "page", defaultValue = "0") int page) {
		return new ResponseEntity<List<Customer>>(customerService.findAllByPaging(page).toList(), HttpStatus.OK);
	}
	
	//find by customer id api
	@GetMapping("/byId/{id}")
	public ResponseEntity<Customer> findById(@PathVariable String id){
		return new ResponseEntity<Customer>(customerService.findById(id), HttpStatus.OK);
	}

	//exception handler
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<String> re(@RequestBody RuntimeException re) {
		return new ResponseEntity<String>(re.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	

}
