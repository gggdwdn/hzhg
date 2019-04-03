package com.aptech.business.taskManagement.exception;

import com.aptech.framework.exception.request.BadRequestException;

public class TaskManagementException extends BadRequestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4086980797104872418L;

	public TaskManagementException(TaskManagementExceptionType exceptionType){
		this(exceptionType.getErrorMsg(),exceptionType.getErrorCode());
	}

	public TaskManagementException(String message) {
		super(message);
	}

	public TaskManagementException(String message, String errorCode, Throwable cause) {
		super(message, errorCode, cause);
	}

	public TaskManagementException(String message, String errorCode) {
		super(message, errorCode);
	}

	public TaskManagementException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

}
