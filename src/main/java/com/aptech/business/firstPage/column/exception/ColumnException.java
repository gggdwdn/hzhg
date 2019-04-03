package com.aptech.business.firstPage.column.exception;

import com.aptech.framework.exception.request.BadRequestException;

public class ColumnException extends BadRequestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4086980797104872418L;

	public ColumnException(ColumnExceptionType exceptionType){
		this(exceptionType.getErrorMsg(),exceptionType.getErrorCode());
	}

	public ColumnException(String message) {
		super(message);
	}

	public ColumnException(String message, String errorCode, Throwable cause) {
		super(message, errorCode, cause);
	}

	public ColumnException(String message, String errorCode) {
		super(message, errorCode);
	}

	public ColumnException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

}
