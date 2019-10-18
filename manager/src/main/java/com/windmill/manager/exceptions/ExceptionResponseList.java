package com.windmill.manager.exceptions;

import java.util.List;

/**
 * @author z022921
 *
 *
 *         Standard Exception payload class
 */
public class ExceptionResponseList {
	private List<ExceptionResponse> errors;

	private ExceptionResponseList() {
		super();
	}

	public ExceptionResponseList(List<ExceptionResponse> errors) {
		this.errors = errors;
	}

	public List<ExceptionResponse> getErrors() {
		return errors;
	}

}
