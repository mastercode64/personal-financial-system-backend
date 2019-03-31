package com.mastercode.personalfinancialsystem.exception;

public class UniqueFieldException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UniqueFieldException() {
		super();
	}

	public UniqueFieldException(String message) {
		super(message);
	}

}
