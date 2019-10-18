package com.windmill.manager.exceptions;

/**
 * @author z022921
 *
 *
 *         Exception Class for APIs
 */
public class ExceptionResponse {
	
	private int errorCode;
	private String errorMessage;
	private ErrorLevel errorLevel;
	private ErrorType errorType;
	

	public ExceptionResponse(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ErrorLevel getErrorLevel() {
		return errorLevel;
	}

	public void setErrorLevel(ErrorLevel errorLevel) {
		this.errorLevel = errorLevel;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public static final class ExceptionResponseBuilder {

		private int errorCode;
		private String errorMessage;
		private ErrorLevel errorLevel;
		private ErrorType errorType;
		@SuppressWarnings("unused")
		private ExceptionResponseBuilder() {
		}

		public ExceptionResponseBuilder(int errorCode, String errorMessage) {
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
		}

		public ExceptionResponseBuilder withErrorLevel(ErrorLevel errorLevel) {
			this.errorLevel = errorLevel;
			return this;
		}

		public ExceptionResponseBuilder withErrorType(ErrorType errorType) {
			this.errorType = errorType;
			return this;
		}

		public ExceptionResponse build() {
			ExceptionResponse exceptionResponse = new ExceptionResponse(this.errorCode, this.errorMessage);
			if (null != this.errorLevel) {
				exceptionResponse.errorLevel = this.errorLevel;
			}

			if (null != this.errorType) {
				exceptionResponse.errorType = this.errorType;
			}
			return exceptionResponse;
		}

	}

}
