package com.training.service;

import java.util.List;

import org.springframework.core.io.Resource;

import com.training.entity.Document;
import com.training.util.DocumentType;

public interface DocumentService {

	Resource getDocumentForCustomer(Long customerId, DocumentType docType);

	List<Document> getDocumentListForCustomer(Long customerId);

}
