package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy =  GenerationType.UUID)
	private String customerId;
	private String firstName;
	private String lastName;
	private String street;
	private String adress;
	private String city;
	private String state;
	private String email;
	private String phone;
	
}
