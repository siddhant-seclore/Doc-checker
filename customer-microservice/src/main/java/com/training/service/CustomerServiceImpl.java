package com.training.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.entity.Customer;
import com.training.entity.Document;
import com.training.repository.CustomerRepository;
import com.training.repository.DocumentRepository;
import com.training.util.Constants;
import com.training.util.CustomerUtils;
import com.training.util.DocumentType;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	DocumentStrorageService documentStrorageService;
	
	@Override
	public Customer addCustomer(String customer,MultipartFile aadharFile,MultipartFile panFile) throws JsonMappingException, JsonProcessingException {
		Customer customerObj = mapToCustomer(customer);
		if(aadharFile!=null) {
			String fileName=CustomerUtils.getConcatanatedName(customerObj)+Constants.AADHAR+".pdf";
		documentStrorageService.save(aadharFile,fileName);
		Document aadharObj = new Document(DocumentType.AADHAR_CARD,fileName);
		customerObj.addDocument(aadharObj);
		}
		if(panFile!=null) {
			String fileName=CustomerUtils.getConcatanatedName(customerObj)+Constants.PAN+".pdf";
			documentStrorageService.save(panFile,fileName);
			Document panObj = new Document(DocumentType.PAN_CARD,fileName);
			customerObj.addDocument(panObj);
		}
		return	customerRepository.save(customerObj);
	}	
	
	@Override
	public void updateCustomers(List<Customer> customers) {
		for(Customer c: customers) {
			customerRepository.save(c);
		}
	}
	
	@Override
	public List<Customer> getAllCustomer(){
		return customerRepository.findAll();
		}
	
	@Override
	public Customer getCustomer(Long customerId) {
		return customerRepository.findById(customerId).get();
	}
	
	private Customer mapToCustomer(String customer) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(customer, Customer.class);
		
	}

	@Override
	public List<Customer> getCustomerToVerify() {
		return customerRepository.findToBeVerified();
	}

}
