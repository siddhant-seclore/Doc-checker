package com.training.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.dto.CustomerDto;
import com.training.dto.DocumentDto;
import com.training.util.DocumentUtils;

@Service
public class VerificationService {
	
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 @Value("file.storage.url.local")
	 private String localStorageUrl;
	 
	 @Value("${customerServiceUrl}")
	 private String customerServiceUrl;
	 
	 private final long verificationScheduledRate=60000;
	 
	 private final Path root = Paths.get("D:\\training\\eclipse workspaces\\Doc checker\\uploads");
	 
	 @Scheduled(fixedRate=verificationScheduledRate)
	 public void verificationTask() {
		 List<CustomerDto> updatedCustomers= RunVerification();
		 updateVerificationStatus(updatedCustomers);
	 }
	 
		public List<CustomerDto> RunVerification() {
			List<CustomerDto> customersToVerify = getCustomerToVerify();
			for(CustomerDto customer: customersToVerify) {
				verifyCustomer(customer);
			}
			return customersToVerify;
		}
	public List<CustomerDto> getCustomerToVerify() {
	final String customerDocApi = customerServiceUrl + "/customer/toVerify";
		 List<CustomerDto> resultList= restTemplate.getForObject(customerDocApi, List.class);
		 ObjectMapper mapper= new ObjectMapper();
		return mapper.convertValue(resultList, new TypeReference<List<CustomerDto>>() { });
	}
	

	
	public CustomerDto verifyCustomer(CustomerDto customer) {
		if(!customer.getDocumentList().isEmpty()) {
			for(DocumentDto doc: customer.getDocumentList()) {
				String docText=extractNameInDoc(doc.getFileName());
				if(DocumentUtils.checkifContainsName(docText, customer.getName())) {
					doc.setVerified(true);
				}
			}
		}
		return customer;
	}
	
	private String extractNameInDoc(String fileName) {
		Resource resource = load(fileName);
		   String text;

	        try (final PDDocument document = PDDocument.load(resource.getInputStream())) {
	            final PDFTextStripper pdfStripper = new PDFTextStripper();
	            text = pdfStripper.getText(document);
	        } catch (final Exception ex) {
	            text = "Error parsing PDF";
	        }
	        return text;
	}
	
	  private Resource load(String filename) {
		    try {
		      Path file = root.resolve(filename);
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    } catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
		  }
	  
	  private void updateVerificationStatus(List<CustomerDto> customers) {
		  final String customerDocApi = customerServiceUrl + "/document/updateStatus";
			 restTemplate.postForObject(customerDocApi,customers , ResponseEntity.class);
			 
	  }
}
