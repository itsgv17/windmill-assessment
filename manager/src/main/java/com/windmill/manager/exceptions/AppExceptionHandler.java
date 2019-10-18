package com.windmill.manager.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ExceptionResponseList> trmException(AppException ex) {

		return new ResponseEntity<>(new ExceptionResponseList(ex.getExceptions()), ex.getHttpStatus());
	}
}
