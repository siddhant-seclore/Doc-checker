package com.training.entity;


import com.training.util.DocumentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="documents")
@Getter
@Setter
public class Document {

	
	public Document() {
		super();
	}

	public Document(DocumentType docType, String fileName) {
		this.documentType=docType;
		this.fileName=fileName;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	@Column(name="doc_id")
	private Long id;
	
	private DocumentType documentType;
	
	private String fileName;
	
	
	private Boolean verified=false;
	
}
