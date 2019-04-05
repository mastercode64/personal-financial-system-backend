package com.mastercode.personalfinancialsystem.exception;

public class ValidationErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidationErrorException() {
		super();
	}

	public ValidationErrorException(String message) {
		super(message);
	}

}
