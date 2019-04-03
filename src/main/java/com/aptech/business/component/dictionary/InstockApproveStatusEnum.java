package com.aptech.business.component.dictionary;

import com.aptech.common.system.dictionary.domain.BaseCodeEnum;

public enum InstockApproveStatusEnum implements BaseCodeEnum {
	
	PENDING(0, "0", "待提交"),//pending 
	REJECT(1, "1", "驳回"),//reject 
	STOREKEEPER(2, "2", "待库管员审批"),//storekeeper 
	DIRECTORAPPROVE(3, "3", "待检修主任审核"),//directorapprove 
	END(4, "4", "审核通过"),//END
	CANCEL(5, "5", "取消流程");//CANCEL

	private int id;
	
	private String code;
	
	private String name;
	
	InstockApproveStatusEnum(Integer id, String code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}


	public String getCode() {
		return code;
	}


	public String getName() {
		return name;
	}

	
	
}
