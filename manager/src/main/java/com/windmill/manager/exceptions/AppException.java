package com.windmill.manager.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;



public class AppException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private  final List<ExceptionResponse> exceptions;
	private final HttpStatus httpStatus;

	public AppException(String message, HttpStatus httpStatus, List<ExceptionResponse> exceptions) {
		super(message);
		this.exceptions = exceptions;
		this.httpStatus = httpStatus;
	}

	public List<ExceptionResponse> getExceptions() {
		return exceptions;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
