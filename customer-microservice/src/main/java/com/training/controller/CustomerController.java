package com.training.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.training.entity.Customer;
import com.training.entity.Document;
import com.training.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customer/add")
	public String addCustomer(@RequestPart(name = "customer") String customer,
			@RequestPart(name = "aadhar",required = false) MultipartFile aadhar,
			@RequestPart(name = "pan",required = false)  MultipartFile pan ) 
					throws JsonMappingException, JsonProcessingException {
Customer dbCustomer = customerService.addCustomer(customer,aadhar,pan);	
		return String.format("Customer [%s] added Succesfully with id [%d]",dbCustomer.getName(),dbCustomer.getId());
	}

	
	@PutMapping("/customer/update")
	public void putCustomer(@RequestPart(name = "customer") String customer,
			@RequestPart(name = "aadhar",required = false) MultipartFile aadhar,
			@RequestPart(name = "pan",required = false)  MultipartFile pan ) 
					throws JsonMappingException, JsonProcessingException {
		//TODO: add update functionality
//Customer dbCustomer = customerService.addCustomer(customer,aadhar);	
//		return String.format("Customer [%s] updated Succesfully with id [%d]",dbCustomer.getName(),dbCustomer.getId());
	}
	
	@GetMapping("/customers")
	public List<Customer> getCustomer() {
		return customerService.getAllCustomer();
	}
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable Long customerId) {
		return customerService.getCustomer(customerId);
	}
	
	@GetMapping("/customer/toVerify")
	public List<Customer> getCustomersToVerify() {
		return customerService.getCustomerToVerify();
	}
	

	
}
