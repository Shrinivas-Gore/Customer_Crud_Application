# Customer CRUD Application

## Introduction

This project is a CRUD application for managing customer data. It features a frontend built with HTML, CSS, and JavaScript, and a backend implemented using Spring Boot. The application uses MySQL for data storage.
we can search customer based on selected field ex. firstName, lastName, etc.

## Features

- Create a new customer
- Update existing customer information
- Retrieve a list of customers with pagination, sorting, and searching
- Retrieve a single customer based on ID
- Delete a customer

## Technologies Used

- Backend: Spring Boot
- Frontend: HTML, CSS, JavaScript
- Database: MySQL

## server pot
-9090

## API Endpoints

-Create a Customer: POST /customer/customers
-Update a Customer: PUT /customer/update
-Get Customer List: GET /customer/findAll (supports pagination, sorting, and searching)
-Get Single Customer: GET /customer/byId/{id}


## HTML Pages
-List of Customers customer.html
-update customer update.html
-registration of customer newCustomer.html

