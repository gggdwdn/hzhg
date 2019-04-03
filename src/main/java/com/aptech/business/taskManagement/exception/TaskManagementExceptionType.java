package com.aptech.business.taskManagement.exception;



public class TaskManagementExceptionType {
	
	private String errorCode ;
	private String errorMsg ;	
	
	public static TaskManagementExceptionType DEFECT_CODE_NULL = new TaskManagementExceptionType("100001", "该记录已删除！");
	public static TaskManagementExceptionType DEFECT_CODE_REPEAT  = new TaskManagementExceptionType("100002", "状态已改变，请刷新！");
	public static TaskManagementExceptionType DEFECT_CODE_STATUS  = new TaskManagementExceptionType("100003", "该记录不是待提交或驳回状态，不能该操作！");
	public static TaskManagementExceptionType DEFECT_CODE_REJECT  = new TaskManagementExceptionType("100004", "该记录驳回状态，请在流程中提交！");
	private TaskManagementExceptionType(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
