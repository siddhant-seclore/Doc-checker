package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.Customer;
import com.training.entity.Document;
import com.training.service.CustomerService;
import com.training.service.DocumentService;
import com.training.util.DocumentType;

@RestController
public class DocumentController {
	@Autowired
	DocumentService documentService;
	
	@Autowired
	CustomerService customerService;
		
	@GetMapping("/document")
	public ResponseEntity<Resource> getDocumentForCustomer(@RequestParam Long customerId,@RequestParam char docType)
	{
		Resource doc= documentService.getDocumentForCustomer(customerId,DocumentType.getFromDocChar(docType));
		return new ResponseEntity<>( doc,HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public List<Document> getStatus(@RequestParam Long customerId) {
		return documentService.getDocumentListForCustomer(customerId);
	}
	
	@PostMapping("/document/updateStatus")
	public void updateVerificationStatus(@RequestBody List<Customer> customers) {
		customerService.updateCustomers(customers);
	}

}
