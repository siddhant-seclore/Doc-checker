package com.training.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
private Long id;
	
	private String name;
	
	private List<DocumentDto> documentList;
}
