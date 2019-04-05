package com.mastercode.personalfinancialsystem.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError {

	private Long timestamp;
	private Integer status;
	private String error;
	private Object message;
	private String path;

	public StandardError() {
	}

	public StandardError(Long timestamp, Integer status, String error, Object message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

}
