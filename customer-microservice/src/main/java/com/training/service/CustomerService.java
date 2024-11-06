package com.training.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.training.entity.Customer;

public interface CustomerService {

	Customer addCustomer(String customer, MultipartFile aadharFile, MultipartFile panFile)
			throws JsonMappingException, JsonProcessingException;

	void updateCustomers(List<Customer> customers);

	List<Customer> getAllCustomer();

	Customer getCustomer(Long customerId);

	List<Customer> getCustomerToVerify();

}
