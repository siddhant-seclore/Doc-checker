package com.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.service.VerificationService;

@RestController
public class VerificationController {

	@Autowired
	VerificationService verificationService;
	
	@GetMapping("/verify")
	public void verify(){
		verificationService.verificationTask();
		
	}
	
}
