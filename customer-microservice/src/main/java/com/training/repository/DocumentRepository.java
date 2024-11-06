package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.entity.Document;
import com.training.util.DocumentType;

public interface DocumentRepository extends JpaRepository<Document, Long>{

	@Query("select d from Customer c JOIN c.documentList d where c.id =:customerId and  d.documentType =:documentType")
public Document findBy(Long customerId, DocumentType documentType);
	
	@Query("select d from Customer c JOIN c.documentList d where c.id =:customerId")
public List<Document> findBy(Long customerId);
}
