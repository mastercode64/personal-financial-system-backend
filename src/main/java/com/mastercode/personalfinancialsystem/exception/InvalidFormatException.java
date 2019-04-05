package com.mastercode.personalfinancialsystem.exception;

public class InvalidFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidFormatException() {
		super();
	}

	public InvalidFormatException(String message) {
		super(message);
	}

}
