package com.mastercode.personalfinancialsystem.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mastercode.personalfinancialsystem.exception.ResourceNotFoundException;
import com.mastercode.personalfinancialsystem.exception.StandardError;
import com.mastercode.personalfinancialsystem.exception.UniqueFieldException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> notFound(ResourceNotFoundException ex, HttpServletRequest request){		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError error =	new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Resource not found",
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(UniqueFieldException.class)
	public ResponseEntity<?> uniqueField(UniqueFieldException ex, HttpServletRequest request){		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError error =	new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Field is unique",
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
}
