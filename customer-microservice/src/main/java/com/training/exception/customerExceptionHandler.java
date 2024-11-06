package com.training.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestControllerAdvice
public class customerExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorObj> handle(NoSuchElementException e) {
		ErrorObj obj = new ErrorObj();
		obj.setErrorMessage("No such customer exists!");
		return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ErrorObj> handle(JsonMappingException e) {
		ErrorObj obj = new ErrorObj();
		obj.setErrorMessage("Invalid Customer Data!");
		return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<ErrorObj> handle(JsonProcessingException e) {
		ErrorObj obj = new ErrorObj();
		obj.setErrorMessage("Invalid Customer Data!");
		return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
	}
	public static class ErrorObj {
		private String errorMessage;
		
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
	
}
}