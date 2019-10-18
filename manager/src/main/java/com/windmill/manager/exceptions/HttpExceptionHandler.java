package com.windmill.manager.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.windmill.manager.constant.AppLevelErrorConstant;

/**
 * @author z022921
 *
 *         Common Rest Related Exception Handler
 */
@ControllerAdvice
public class HttpExceptionHandler {

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResponseList> httpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException ex) {

		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported method(s) -  ");
		builder.append(
				ex.getSupportedHttpMethods().stream().map(HttpMethod::toString).collect(Collectors.joining(", ")));
		int value = AppLevelErrorConstant.METHOD_NOT_SUPPORTED;
		String message = builder.toString();

		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(value, message)
				.withErrorLevel(ErrorLevel.TECHNICAL).withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionResponseList> httpMessageNotReadableException(
			final HttpMessageNotReadableException ex) {

		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(
				AppLevelErrorConstant.MESSAGE_NOT_READABLE, "Invalid Request body payload")
						.withErrorLevel(ErrorLevel.TECHNICAL).withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ExceptionResponseList> httpMediaTypeNotSupportedException(
			final HttpMediaTypeNotSupportedException ex) {

		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media type(s) - ");
		builder.append(ex.getSupportedMediaTypes().stream().map(MediaType::toString).collect(Collectors.joining(", ")));

		int value = AppLevelErrorConstant.MEDIA_TYPE_NOT_SUPPORTED;
		String message = builder.toString();

		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(value, message)
				.withErrorLevel(ErrorLevel.TECHNICAL).withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<ExceptionResponseList> httpMediaTypeNotAcceptableException(
			final HttpMediaTypeNotAcceptableException ex) {

		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getLocalizedMessage());
		builder.append("Supported media type(s) - ");
		builder.append(ex.getSupportedMediaTypes().stream().map(MediaType::toString).collect(Collectors.joining(", ")));

		int value = AppLevelErrorConstant.MEDIA_TYPE_NOT_SUPPORTED;
		String message = builder.toString();

		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(value, message)
				.withErrorLevel(ErrorLevel.TECHNICAL).withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponseList> methodArgumentNotValidException(
			final MethodArgumentNotValidException ex) {

		final List<String> errors = new ArrayList<>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(
				AppLevelErrorConstant.METHOD_ARGUMENT_NOT_VALID, errors.toString()).withErrorLevel(ErrorLevel.TECHNICAL)
						.withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionResponseList> noHandlerFoundException(final NoHandlerFoundException ex) {

		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(
				AppLevelErrorConstant.NO_HANDLER_FOUND, error).withErrorLevel(ErrorLevel.TECHNICAL)
						.withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ExceptionResponseList> methodArgumentTypeMismatchException(
			final MethodArgumentTypeMismatchException ex) {

		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(
				AppLevelErrorConstant.METHOD_ARGUMENT_TYPE_MISMATCH, error).withErrorLevel(ErrorLevel.TECHNICAL)
						.withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ExceptionResponseList> missingServletRequestParameterException(
			final MissingServletRequestParameterException ex) {

		final String error = ex.getParameterName() + " parameter is missing";
		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(
				AppLevelErrorConstant.MISSING_SERVLET_REQUEST_PARAMETER, error).withErrorLevel(ErrorLevel.TECHNICAL)
						.withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<ExceptionResponseList> missingServletRequestPartException(
			final MissingServletRequestPartException ex) {
		final String error = ex.getRequestPartName() + " part is missing";
		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(
				AppLevelErrorConstant.MISSING_SERVLET_REQUEST_PART, error).withErrorLevel(ErrorLevel.TECHNICAL)
						.withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<ExceptionResponseList> typeMismatchException(final TypeMismatchException ex) {

		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		ExceptionResponse response = new ExceptionResponse.ExceptionResponseBuilder(AppLevelErrorConstant.TYPE_MISMATCH,
				error).withErrorLevel(ErrorLevel.TECHNICAL).withErrorType(ErrorType.ERROR).build();

		return new ResponseEntity<>(new ExceptionResponseList(Arrays.asList(response)), HttpStatus.BAD_REQUEST);

	}
}
