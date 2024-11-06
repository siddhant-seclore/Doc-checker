package com.training.dto;



import com.training.util.DocumentType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDto {
	private Long id;
	
	private DocumentType documentType;
	
	private String fileName;
	
	private Boolean verified;
}
