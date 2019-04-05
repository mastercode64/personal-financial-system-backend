package com.mastercode.personalfinancialsystem.exceptionhandler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mastercode.personalfinancialsystem.exception.InvalidFormatException;
import com.mastercode.personalfinancialsystem.exception.ResourceNotFoundException;
import com.mastercode.personalfinancialsystem.exception.StandardError;
import com.mastercode.personalfinancialsystem.exception.ValidationErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

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
	
	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<?> validationError(MethodArgumentNotValidException ex,
	 * HttpServletRequest request) { HttpStatus status = HttpStatus.BAD_REQUEST;
	 * BindingResult result = ex.getBindingResult(); final List<FieldError>
	 * fieldErrors = result.getFieldErrors();
	 * 
	 * var errorList = (FieldError[])(fieldErrors.toArray(new
	 * FieldError[fieldErrors.size()]));
	 * 
	 * StandardError error = new StandardError( System.currentTimeMillis(),
	 * status.value(), "Validation error", errorList, request.getRequestURI());
	 * 
	 * return ResponseEntity.status(status).body(error); }
	 */
	
	@ExceptionHandler(ValidationErrorException.class)
	public ResponseEntity<?> validationError(ValidationErrorException ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError error =	new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Validation error",
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<?> invalidFormat(InvalidFormatException ex, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError error =	new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Invalid format",
				ex.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
}
