package com.aptech.business.firstPage.column.exception;



public class ColumnExceptionType {
	
	private String errorCode ;
	private String errorMsg ;	
	
	public static ColumnExceptionType COLUMN_CODE_TITLE = new ColumnExceptionType("20001", "栏目标题重复，请重新填写！");
	public static ColumnExceptionType COLUMN_CODE_URL = new ColumnExceptionType("20002", "栏目链接地址重复，请重新填写！");
	public static ColumnExceptionType COLUMN_CODE_USED  = new ColumnExceptionType("20003", "该栏目已配置首页无法删除！");
	public static ColumnExceptionType COLUMN_CODE_FIXED  = new ColumnExceptionType("20004", "固定类型栏目，无法删除！");
	private ColumnExceptionType(String errorCode, String errorMsg) {
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
