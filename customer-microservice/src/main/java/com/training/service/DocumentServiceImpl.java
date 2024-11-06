package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.training.entity.Document;
import com.training.repository.DocumentRepository;
import com.training.util.DocumentType;

@Service
public class DocumentServiceImpl implements DocumentService{
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	DocumentStrorageService documentStorageService;

	@Override
	public Resource getDocumentForCustomer(Long customerId, DocumentType docType) {
		
		Document document = documentRepository.findBy(customerId,docType);
		
		if(document!=null) 
			return documentStorageService.load(document.getFileName());
		
		return null;
	}
	
	@Override
	public List<Document> getDocumentListForCustomer(Long customerId){
		return documentRepository.findBy(customerId);
	}
}
